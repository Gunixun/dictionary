package gunixun.repository.room

import gunixun.model.DataModel
import gunixun.model.History
import gunixun.repository.room.dao.HistoryDao
import gunixun.repository.room.entities.HistoryEntity
import gunixun.repository.TranslationLocalRepo

class RoomTranslationLocalRepoImpl(
    private val historyDao: HistoryDao
) : TranslationLocalRepo {

    override suspend fun saveToDB(data: List<DataModel>) {
        if (data.isNotEmpty()){
            historyDao.insert(HistoryEntity(data[0].text))
        }
    }

    override suspend fun getAll(): List<History> {
        return convertRepoEntityToEntity(historyDao.all())
    }
}