package gunixun.dictionary.ui.history

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import gunixun.dictionary.domain.entities.History


class HistoryAdapter() : RecyclerView.Adapter<HistoryViewHolder>() {

    private var listData: MutableList<History> = arrayListOf()

    fun setData(data: List<History>) {
        val diffResult = DiffUtil.calculateDiff(
            DiffUtilsCallback(newItems = data, oldItems = listData)
        )
        diffResult.dispatchUpdatesTo(this)
        listData.clear()
        listData.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.createView(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size
}