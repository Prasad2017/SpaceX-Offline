package com.example.spacex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spacex.model.database.RocketsEntity


@Database(entities = [RocketsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    /**
     * Connects the database to the DAO.
     */
    abstract val rocketsDao: RocketsDao

    companion object {

        @Volatile
        private lateinit var INSTANCE: AppDatabase

        fun getDatabase(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                if (!Companion::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        "rockets").build()
                }
            }
            return INSTANCE
        }

    }

}


