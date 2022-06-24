package gunixun.model

data class DataModel (
    val id: Int,
    val text: String,
    val meanings: List<Meaning>
)