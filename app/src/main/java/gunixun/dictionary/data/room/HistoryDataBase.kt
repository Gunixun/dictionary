package gunixun.dictionary.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import gunixun.dictionary.data.room.dao.HistoryDao
import gunixun.dictionary.data.room.entities.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}
