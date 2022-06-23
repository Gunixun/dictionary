package gunixun.dictionary.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import gunixun.dictionary.databinding.FragmentDetailsItemBinding
import gunixun.dictionary.domain.entities.Meaning

const val httpsString = "https:"

class TranslationDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun createView(parent: ViewGroup): TranslationDetailsViewHolder {
            val binding = FragmentDetailsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return TranslationDetailsViewHolder(binding.root)
        }
    }

    fun bind(meaning: Meaning) {
        FragmentDetailsItemBinding.bind(itemView).apply {
            wordTextView.text = meaning.translationText
            noteTextView.text = meaning.translationNote
            transcriptionTextView.text = meaning.transcription
            previewImageView.load(httpsString + meaning.previewUrl)
        }
    }
}