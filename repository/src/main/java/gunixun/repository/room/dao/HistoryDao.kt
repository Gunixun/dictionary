package gunixun.repository.room.dao

import androidx.room.*
import gunixun.repository.room.entities.HistoryEntity

@Dao
interface HistoryDao {

    @Query("SELECT * FROM HistoryEntity")
    suspend fun all(): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: HistoryEntity)
}