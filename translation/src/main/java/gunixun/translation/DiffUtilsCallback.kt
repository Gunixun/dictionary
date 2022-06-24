package gunixun.translation

import androidx.recyclerview.widget.DiffUtil
import gunixun.model.DataModel


class DiffUtilsCallback (
    private val newItems: List<DataModel>,
    private val oldItems: List<DataModel>
) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        return newItems[newPos].id == oldItems[oldPos].id
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        val oldItem = oldItems[oldPos]
        val newItem = newItems[newPos]
        return oldItem.text == newItem.text
                && oldItem.meanings == newItem.meanings
    }
}