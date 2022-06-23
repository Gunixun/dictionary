package gunixun.dictionary.ui.details

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import gunixun.dictionary.domain.entities.Meaning

class DetailsAdapter() : RecyclerView.Adapter<DetailsViewHolder>() {

    private var listData: MutableList<Meaning> = arrayListOf()

    fun setData(data: List<Meaning>) {
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder.createView(parent)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size
}