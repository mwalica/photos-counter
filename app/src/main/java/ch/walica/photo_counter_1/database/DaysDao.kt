package ch.walica.photo_counter_1.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DaysDao {

    @Upsert
    suspend fun upsertDay(day: Day)

    @Delete
    suspend fun deleteDay(day: Day)

    @Query("SELECT * FROM days ORDER BY date DESC")
    fun getDays(): Flow<List<Day>>

}