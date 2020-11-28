package com.lucaramos.howistheweather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lucaramos.howistheweather.dao.CityDatabaseDao
import com.lucaramos.howistheweather.model.CityDatabase

@Database(entities = arrayOf(CityDatabase::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cityDatabaseDao() : CityDatabaseDao

    companion object {
        private var Instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if(Instance == null){
                synchronized(AppDatabase::class){
                    Instance = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, "HowIsTheWeather.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return Instance
        }
    }
}