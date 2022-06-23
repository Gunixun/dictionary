package gunixun.dictionary.domain.entities

data class DataModel (
    val id: Int,
    val text: String,
    val meanings: List<Meaning>
)