package ch.walica.photo_counter_1.database

import androidx.room.Database

@Database(entities = [Day::class], version = 1)
abstract class DaysDatabase {
    abstract val dao: DaysDao
}