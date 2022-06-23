package gunixun.dictionary.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gunixun.dictionary.databinding.FragmentHistoryItemBinding
import gunixun.dictionary.domain.entities.History

class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun createView(parent: ViewGroup): HistoryViewHolder {
            val binding = FragmentHistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return HistoryViewHolder(binding.root)
        }
    }

    fun bind(history: History) {
        FragmentHistoryItemBinding.bind(itemView).apply {
            wordTextView.text = history.word
        }
    }
}