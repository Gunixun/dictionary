package gunixun.dictionary.data.room.dao

import androidx.room.*
import gunixun.dictionary.data.room.entities.HistoryEntity

@Dao
interface HistoryDao {

    @Query("SELECT * FROM HistoryEntity")
    suspend fun all(): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: HistoryEntity)
}