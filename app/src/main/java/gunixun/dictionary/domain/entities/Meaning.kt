package gunixun.dictionary.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meaning (
    val id: Int,
    val translationText: String,
    val translationNote: String,
    val previewUrl: String,
    val imageUrl: String,
    val transcription: String
): Parcelable