package com.lucaramos.howistheweather.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityDatabase(@PrimaryKey val id: Long, val cityName: String)
