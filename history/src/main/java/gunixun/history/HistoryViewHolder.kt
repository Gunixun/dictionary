package gunixun.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gunixun.history.databinding.FragmentHistoryItemBinding
import gunixun.model.History

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

    fun bind(history: History, listener: (History) -> Unit) {
        FragmentHistoryItemBinding.bind(itemView).apply {
            wordTextView.text = history.word
            itemView.setOnClickListener {
                listener.invoke(history)
            }
        }
    }
}