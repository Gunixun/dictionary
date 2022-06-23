package gunixun.dictionary.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import gunixun.dictionary.R
import gunixun.dictionary.databinding.FragmentDetailsBinding
import gunixun.dictionary.ui.BaseFragment
import gunixun.dictionary.ui.utils.*
import gunixun.dictionary.ui.utils.MAX_RETRY_ITER
import gunixun.dictionary.ui.utils.RESET_RETRY_ITER
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment :
    BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate)
{

    private val viewModel: DetailsContract.DetailsViewModel by viewModel()
    private lateinit var adapter: DetailsAdapter
    private var word: String = ""

    private var retryIter: Int = RESET_RETRY_ITER
    private var snackBar: Snackbar? = null

    companion object {
        const val ARG_PARAM = "word"

        fun newInstance(word: String): DetailsFragment {
            return DetailsFragment().also { fragment ->
                fragment.arguments = Bundle().also { bundle ->
                    bundle.putString(ARG_PARAM, word)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        word = arguments?.getString(ARG_PARAM, "").toString()

        setupUi()
        connectSignals()
        viewModel.findWord(word)
    }

    private fun setupUi() {
        adapter = DetailsAdapter()
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
            is AppState.SuccessDataModel -> {
                retryIter = RESET_RETRY_ITER
                if (appState.data == null) {
                    binding.emptyTextView.isVisible = true
                } else {
                    binding.wordTextView.text = appState.data.text
                    adapter.setData(appState.data.meanings)
                }
            }
            is AppState.Error -> {
                binding.emptyTextView.isVisible = true
                if (retryIter < MAX_RETRY_ITER) {
                    snackBar = binding.root.createErrSnackBar(
                        text = appState.error.toString(),
                        actionText = R.string.retry,
                        { viewModel.findWord(word) }
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