package gunixun.dictionary.ui.translation_details

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import gunixun.dictionary.domain.entities.Meaning

class TranslationDetailsAdapter() : RecyclerView.Adapter<TranslationDetailsViewHolder>() {

    private var listData: MutableList<Meaning> = arrayListOf()

    fun setData(data: List<Meaning>) {
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationDetailsViewHolder {
        return TranslationDetailsViewHolder.createView(parent)
    }

    override fun onBindViewHolder(holder: TranslationDetailsViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size
}