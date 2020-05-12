package com.baliwork.trainingcrudsqlite.sqlite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.baliwork.trainingcrudsqlite.sqlite.dao.BiodataDao
import com.baliwork.trainingcrudsqlite.sqlite.table.Biodata

@Database(
    entities = [Biodata::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun biodataDao(): BiodataDao

    companion object {
        private lateinit var instance: AppDatabase
        fun getInstance(): AppDatabase = instance
        fun createDatabase(context: Context) {
            instance = Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "db_oxy"
            ).allowMainThreadQueries().build()
        }
    }
}