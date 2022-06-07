package gunixun.dictionary.ui.translation

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import gunixun.dictionary.R
import gunixun.dictionary.databinding.FragmentTranslationBinding
import gunixun.dictionary.ui.BaseFragment
import gunixun.dictionary.ui.utils.AppState
import gunixun.dictionary.ui.utils.createErrSnackBar
import gunixun.dictionary.ui.utils.createMsgSnackBar
import gunixun.dictionary.ui.utils.hideSnackBar
import org.koin.android.ext.android.inject

class TranslationFragment :
    BaseFragment<FragmentTranslationBinding>(FragmentTranslationBinding::inflate),
    TranslationContract.TranslationViewInterface
{

    private lateinit var adapter: TranslationAdapter
    private val presenter: TranslationContract.TranslationPresenterInterface by inject()

    private var retryIter: Int = 0
    private var snackBar: Snackbar? = null

    companion object {
        fun newInstance() = TranslationFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        connectSignals()
    }

    private fun setupUi() {
        adapter = TranslationAdapter()
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
                presenter.findWord(query)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    override fun renderData(appState: AppState) {
        binding.progressBar.isVisible = false
        binding.emptyTextView.isVisible = false
        when (appState) {
            is AppState.Loading -> {
                hideSnackBar(snackBar)
                binding.progressBar.isVisible= true
            }
            is AppState.Success -> {
                retryIter = 0
                if (appState.data.isEmpty()){
                    binding.emptyTextView.isVisible = true
                }
                adapter.setData(appState.data)
            }
            is AppState.Error -> {
                binding.emptyTextView.isVisible = true
                if (retryIter < 3) {
                    snackBar = binding.root.createErrSnackBar(
                        text = appState.error.toString(),
                        actionText = R.string.retry,
                        { presenter.findWord(binding.wordSearchView.query.toString()) }
                    )
                    snackBar?.show()
                } else {
                    binding.root.createMsgSnackBar(
                        text = this.resources.getString(R.string.fall_load_data)
                    ).show()
                }
                retryIter++
            }
        }
    }

    override fun onDestroy() {
        presenter.onDetachView(this)
        super.onDestroy()
    }

}
