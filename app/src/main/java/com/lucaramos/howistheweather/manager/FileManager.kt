package com.lucaramos.howistheweather.manager

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.RadioGroup

class FileManager(val context : Context) {

    private  var prefs: SharedPreferences?
    private lateinit var rgTemperature: RadioGroup
    private lateinit var rgLanguage: RadioGroup

    private var temperatureUnit  = ""
    private var language = ""

    private val  TEMPERATURE_UNIT = "temperature_unit"
    private val  LANGUAGE = "description_language"

    init {
        prefs = context?.getSharedPreferences("my_wheather_preference", Context.MODE_PRIVATE)
    }

    private fun savePrefs(){
        val editor = prefs?.edit()

        editor?.apply(){
            putString(TEMPERATURE_UNIT, temperatureUnit)
            putString(LANGUAGE, language)
            apply()
        }
        Log.d("LCFR-Debug","SettingsFragments - savePrefs - " +
                "successful saved " +
                "language " + this.language + "| temperature " + this.temperatureUnit)
    }

    private fun loadPrefs() {
        language = prefs?.getString(LANGUAGE,"EN").toString()
        temperatureUnit = prefs?.getString(TEMPERATURE_UNIT, "C").toString()

        Log.d("LCFR-Debug","SettingsFragments  line 112- loadPrefs - " +
                "language " + this.language + "| temperature " + this.temperatureUnit )
    }
}