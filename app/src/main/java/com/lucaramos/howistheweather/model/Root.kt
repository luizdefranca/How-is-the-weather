package com.lucaramos.howistheweather.model

data class Root (
    val message: String,
    val cod: String,
    val count: Long,
    val list: List<City>
)