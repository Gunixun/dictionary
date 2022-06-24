package gunixun.details

import androidx.recyclerview.widget.DiffUtil
import gunixun.model.Meaning

class DiffUtilsCallback (
    private val newItems: List<Meaning>,
    private val oldItems: List<Meaning>
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
        return oldItem.translationText == newItem.translationText
                && oldItem.translationNote == newItem.translationNote
                && oldItem.previewUrl == newItem.previewUrl
                && oldItem.imageUrl == newItem.imageUrl
                && oldItem.transcription == newItem.transcription
    }
}
