package gunixun.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import gunixun.repository.room.dao.HistoryDao
import gunixun.repository.room.entities.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}
