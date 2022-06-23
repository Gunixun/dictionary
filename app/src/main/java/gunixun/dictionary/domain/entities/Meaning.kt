package gunixun.dictionary.domain.entities

data class Meaning (
    val id: Int,
    val translationText: String,
    val translationNote: String,
    val previewUrl: String,
    val imageUrl: String,
    val transcription: String
)