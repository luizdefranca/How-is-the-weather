package com.lucaramos.howistheweather.model

import com.google.gson.annotations.SerializedName

data class City(val id: Long,
                val name: String,
                @SerializedName("feels_like")
                   val feelsLike: String)


