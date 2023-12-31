package ch.walica.photo_counter_1.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(tableName = "days")
data class Day(
    val amount: Int = 1,
    val date: Long,
    val year: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)
