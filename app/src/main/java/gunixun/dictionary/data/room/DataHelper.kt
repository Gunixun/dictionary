package gunixun.dictionary.data.room

import gunixun.dictionary.data.room.entities.HistoryEntity
import gunixun.dictionary.domain.entities.History

fun convertRepoEntityToEntity(history: List<HistoryEntity>): List<History> {
    val res: MutableList<History> = mutableListOf()

    for (h in history) {
        res.add(
            History(word = h.word)
        )
    }
    return res
}