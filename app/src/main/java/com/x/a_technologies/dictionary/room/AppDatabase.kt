package com.x.a_technologies.dictionary.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyWordDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao():MyWordDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context?): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, AppDatabase::class.java, "news_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}