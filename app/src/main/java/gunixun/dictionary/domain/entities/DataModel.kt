package gunixun.dictionary.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel (
    val id: Int,
    val text: String,
    val meanings: List<Meaning>
): Parcelable