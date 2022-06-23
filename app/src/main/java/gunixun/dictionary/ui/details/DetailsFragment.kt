package gunixun.dictionary.ui.details

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import gunixun.dictionary.databinding.FragmentDetailsBinding
import gunixun.dictionary.domain.entities.DataModel
import gunixun.dictionary.ui.BaseFragment

class DetailsFragment :
    BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate)
{

    private lateinit var adapter: DetailsAdapter
    var dataModel: DataModel? = null

    companion object {
        const val ARG_PARAM = "data_model"

        fun newInstance(data: DataModel): DetailsFragment {
            return DetailsFragment().also { fragment ->
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
        adapter = DetailsAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerView.adapter = adapter

    }
}