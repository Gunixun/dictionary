package gunixun.repository.retrofit.dto

data class MeaningDto (
    val id: Int,
    val previewUrl: String,
    val imageUrl: String,
    val translation: TranslationDto,
    val transcription: String,
)