package gunixun.dictionary.data.retrofit.dto

data class DataModelDto (
    val id: Int,
    val text: String,
    val meanings: List<MeaningDto>
)