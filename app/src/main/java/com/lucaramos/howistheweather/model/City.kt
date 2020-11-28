package com.lucaramos.howistheweather.model

import com.google.gson.annotations.SerializedName

data class City(val id: Long,
                val name: String,
                val main : Main,
                val sys: Sys,
                val weather: List<Weather>,
                val wind : Wind)


