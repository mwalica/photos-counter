package ch.walica.photo_counter_1.database


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Day::class], version = 1)
abstract class DaysDatabase : RoomDatabase(){
    abstract val dao: DaysDao
}