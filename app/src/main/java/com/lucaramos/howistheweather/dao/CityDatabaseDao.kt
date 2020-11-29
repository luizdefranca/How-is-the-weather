package com.lucaramos.howistheweather.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucaramos.howistheweather.model.CityDatabase

@Dao
abstract
interface CityDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(cityDatabase: CityDatabase): Long

    @Query("SELECT * FROM citydatabase ORDER BY cityName ASC")
    fun getAllCityDatabase(): MutableList<CityDatabase>

}