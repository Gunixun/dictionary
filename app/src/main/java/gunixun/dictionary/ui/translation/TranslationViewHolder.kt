package gunixun.dictionary.ui.translation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gunixun.dictionary.databinding.FragmentTranslationItemBinding
import gunixun.dictionary.domain.entities.DataModel

class TranslationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun createView(parent: ViewGroup): TranslationViewHolder {
            val binding = FragmentTranslationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return TranslationViewHolder(binding.root)
        }
    }

    fun bind(data: DataModel) {

        val strBuilder = StringBuilder()
        for (i in 0 until data.meanings.size) {
            if (i > 0){
                strBuilder.append(", ")
            }
            strBuilder.append(data.meanings[i].translationText)
        }

        FragmentTranslationItemBinding.bind(itemView).apply {
            translationTextView.text = data.text
            meaningsTextView.text = strBuilder.toString()
        }
    }
}