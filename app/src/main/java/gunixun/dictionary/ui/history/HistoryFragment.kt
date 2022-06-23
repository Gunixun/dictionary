package gunixun.dictionary.ui.history

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import gunixun.dictionary.R
import gunixun.dictionary.databinding.FragmentHistoryBinding
import gunixun.dictionary.ui.BaseFragment
import gunixun.dictionary.ui.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryFragment :
    BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate)
{
    private lateinit var adapter: HistoryAdapter
    private val viewModel: HistoryContract.HistoryViewModel by viewModel()

    private var retryIter: Int = RESET_RETRY_ITER
    private var snackBar: Snackbar? = null

    companion object {
        fun newInstance() = HistoryFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        connectSignals()

        viewModel.getAll()
    }

    private fun setupUi() {
        adapter = HistoryAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerView.adapter = adapter
    }

    private fun connectSignals() {
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
                adapter.setData(appState.data)
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
}