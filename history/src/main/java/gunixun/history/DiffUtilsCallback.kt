package gunixun.history

import androidx.recyclerview.widget.DiffUtil
import gunixun.model.History

class DiffUtilsCallback (
    private val newItems: List<History>,
    private val oldItems: List<History>
) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        return newItems[newPos].word == oldItems[oldPos].word
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        val oldItem = oldItems[oldPos]
        val newItem = newItems[newPos]
        return oldItem.word == newItem.word
    }
}