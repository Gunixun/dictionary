package gunixun.dictionary.ui.history

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import gunixun.dictionary.R
import gunixun.dictionary.databinding.FragmentHistoryBinding
import gunixun.dictionary.domain.entities.History
import gunixun.dictionary.ui.BaseFragment
import gunixun.dictionary.ui.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class HistoryFragment :
    BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate)
{
    private lateinit var adapter: HistoryAdapter
    private val viewModel: HistoryContract.HistoryViewModel by viewModel()

    private var data: List<History> = arrayListOf()

    private var retryIter: Int = RESET_RETRY_ITER
    private var snackBar: Snackbar? = null
    private val controller by lazy { activity as Controller }

    companion object {
        fun newInstance() = HistoryFragment()
    }

    interface Controller {
        fun openDetailsScreen(history: History)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        connectSignals()

        viewModel.getAll()
    }

    private fun setupUi() {
        adapter = HistoryAdapter {
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
        binding.wordFilterSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                adapter.setData(filter(data, query))
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.setData(filter(data, newText))
                return true
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
            is AppState.SuccessHistory -> {
                retryIter = RESET_RETRY_ITER
                if (appState.data.isEmpty()) {
                    binding.emptyTextView.isVisible = true
                }
                data = appState.data
                adapter.setData(filter(data, binding.wordFilterSearchView.query.toString()))
            }
            is AppState.Error -> {
                binding.emptyTextView.isVisible = true
                if (retryIter < MAX_RETRY_ITER) {
                    snackBar = binding.root.createErrSnackBar(
                        text = appState.error.toString(),
                        actionText = R.string.retry,
                        { viewModel.getAll() }
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

    private fun filter(models: List<History>, query: String): List<History> {
        val lowerCaseQuery = query.lowercase(Locale.getDefault())
        val filteredModelList: MutableList<History> = ArrayList()
        for (model in models) {
            val text: String = model.word.lowercase()
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model)
            }
        }
        return filteredModelList
    }
}