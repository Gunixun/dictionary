package gunixun.repository.room

import gunixun.model.History
import gunixun.repository.room.entities.HistoryEntity

fun convertRepoEntityToEntity(history: List<HistoryEntity>): List<History> {
    val res: MutableList<History> = mutableListOf()

    for (h in history) {
        res.add(
            History(word = h.word)
        )
    }
    return res
}