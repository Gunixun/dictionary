package gunixun.dictionary.ui.translation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import gunixun.dictionary.domain.entities.DataModel

class TranslationAdapter(
    private val itemClickCallback: (DataModel) -> Unit
) : RecyclerView.Adapter<TranslationViewHolder>() {

    private var listData: MutableList<DataModel> = arrayListOf()

    fun setData(data: List<DataModel>) {
        val diffResult = DiffUtil.calculateDiff(
            DiffUtilsCallback(newItems = data, oldItems = listData)
        )
        diffResult.dispatchUpdatesTo(this)
        listData.clear()
        listData.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationViewHolder {
        return TranslationViewHolder.createView(parent)
    }

    override fun onBindViewHolder(holder: TranslationViewHolder, position: Int) {
        holder.bind(listData[position], itemClickCallback)
    }

    override fun getItemCount() = listData.size

}