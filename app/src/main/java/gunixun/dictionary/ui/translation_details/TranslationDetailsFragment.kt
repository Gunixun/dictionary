package gunixun.dictionary.ui.translation_details

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import gunixun.dictionary.databinding.FragmentTranslationDetailsBinding
import gunixun.dictionary.domain.entities.DataModel
import gunixun.dictionary.ui.BaseFragment

class TranslationDetailsFragment :
    BaseFragment<FragmentTranslationDetailsBinding>(FragmentTranslationDetailsBinding::inflate)
{

    private lateinit var adapter: TranslationDetailsAdapter
    var dataModel: DataModel? = null

    companion object {
        const val ARG_PARAM = "data_model"

        fun newInstance(data: DataModel): TranslationDetailsFragment {
            return TranslationDetailsFragment().also { fragment ->
                fragment.arguments = Bundle().also { bundle ->
                    bundle.putParcelable(ARG_PARAM, data)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataModel = arguments?.getParcelable(ARG_PARAM)

        setupUi()

        dataModel?.let {
            binding.wordTextView.text = it.text
            adapter.setData(it.meanings)
        }
    }

    private fun setupUi() {
        adapter = TranslationDetailsAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerView.adapter = adapter

    }
}