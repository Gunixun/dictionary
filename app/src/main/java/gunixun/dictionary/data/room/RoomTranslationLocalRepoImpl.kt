package gunixun.dictionary.data.room

import gunixun.dictionary.data.room.dao.HistoryDao
import gunixun.dictionary.data.room.entities.HistoryEntity
import gunixun.dictionary.domain.TranslationLocalRepo
import gunixun.dictionary.domain.entities.DataModel
import gunixun.dictionary.domain.entities.History

class RoomTranslationLocalRepoImpl(
    private val historyDao: HistoryDao
) : TranslationLocalRepo{

    override suspend fun saveToDB(data: List<DataModel>) {
        if (data.isNotEmpty()){
            historyDao.insert(HistoryEntity(data[0].text))
        }
    }

    override suspend fun getAll(): List<History> {
        return convertRepoEntityToEntity(historyDao.all())
    }
}