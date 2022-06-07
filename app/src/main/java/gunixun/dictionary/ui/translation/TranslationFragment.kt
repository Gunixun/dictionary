package gunixun.dictionary.ui.translation

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import gunixun.dictionary.data.mock.MockTranslationRepoImpl
import gunixun.dictionary.databinding.FragmentTranslationBinding
import gunixun.dictionary.ui.BaseFragment
import gunixun.dictionary.ui.utils.AppState

class TranslationFragment :
    BaseFragment<FragmentTranslationBinding>(FragmentTranslationBinding::inflate),
    TranslationContract.TranslationViewInterface
{

    private lateinit var adapter: TranslationAdapter
    private var presenter: TranslationContract.TranslationPresenterInterface? = null

    companion object {
        fun newInstance() = TranslationFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        presenter = TranslationPresenter(MockTranslationRepoImpl())
        presenter?.onAttachView(this)
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
                presenter?.findWord(query)
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
                binding.progressBar.isVisible= true
            }
            is AppState.Success -> {
                if (appState.data.isEmpty()){
                    binding.emptyTextView.isVisible = true
                }
                adapter.setData(appState.data)
            }
            is AppState.Error -> {}
        }
    }

    override fun onDestroy() {
        presenter?.onDetachView(this)
        super.onDestroy()
    }

}
