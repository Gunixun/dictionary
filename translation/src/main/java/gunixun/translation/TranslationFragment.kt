package gunixun.translation

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import gunixun.translation.databinding.FragmentTranslationBinding
import gunixun.core.BaseFragment
import gunixun.model.DataModel
import gunixun.utils.createErrSnackBar
import gunixun.utils.createMsgSnackBar
import gunixun.utils.*
import gunixun.utils.R
import gunixun.utils.AppState
import gunixun.utils.hideSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel


class TranslationFragment :
    BaseFragment<FragmentTranslationBinding>(FragmentTranslationBinding::inflate) {

    private lateinit var adapter: TranslationAdapter
    private val viewModel: TranslationContract.TranslationViewModel by viewModel()
    private val controller by lazy { activity as Controller }

    private var retryIter: Int = RESET_RETRY_ITER
    private var snackBar: Snackbar? = null

    companion object {
        fun newInstance() = TranslationFragment()
    }

    interface Controller {
        fun openDetailsScreen(data: DataModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        connectSignals()
    }

    private fun setupUi() {
        adapter = TranslationAdapter {
            controller.openDetailsScreen(it)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerView.adapter = adapter

    }

    private fun connectSignals() {
        binding.wordSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.findWord(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        viewModel.data.observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    private fun renderData(appState: AppState) {
        binding.progressBar.isVisible = false
        binding.emptyTextView.isVisible = false
        when (appState) {
            is AppState.Loading -> {
                hideSnackBar(snackBar)
                binding.progressBar.isVisible = true
            }
            is AppState.Success -> {
                retryIter = RESET_RETRY_ITER
                if (appState.data.isEmpty()) {
                    binding.emptyTextView.isVisible = true
                }
                adapter.setData(appState.data)
            }
            is AppState.Error -> {
                binding.emptyTextView.isVisible = true
                if (retryIter < MAX_RETRY_ITER) {
                    snackBar = binding.root.createErrSnackBar(
                        text = appState.error.toString(),
                        actionText = R.string.retry,
                        { viewModel.findWord(binding.wordSearchView.query.toString()) }
                    )
                    snackBar?.show()
                } else {
                    binding.root.createMsgSnackBar(
                        text = this.resources.getString(R.string.fall_load_data)
                    ).show()
                }
                retryIter++
            }
            else -> {}
        }
    }

}
