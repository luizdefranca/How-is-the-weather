package com.lucaramos.howistheweather.ui


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lucaramos.howistheweather.R
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {


    private  var prefs: SharedPreferences?
    private lateinit var rgTemperature: RadioGroup
    private lateinit var rgLanguage: RadioGroup

    private var temperatureUnit  = ""
    private var language = ""

    private val  TEMPERATURE_UNIT = "temperature_unit"
    private val  LANGUAGE = "description_language"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val settingsContainer = inflater.inflate(R.layout.fragment_settings, container, false)

        return settingsContainer
    }

    init {
        prefs = context?.getSharedPreferences("my_wheather_preference", Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = context?.getSharedPreferences("my_wheather_preference", Context.MODE_PRIVATE)
        rgTemperature = view.findViewById<RadioGroup>(R.id.rg_temperature_unit)
        rgLanguage = view.findViewById<RadioGroup>(R.id.rg_language)
        bt_save.setOnClickListener{
            onSaveClicked(it)
        }
        loadPrefs()

        setRadioButton()

    }
    private fun onSaveClicked(view: View){

        val checkedTemperatureRadioButtonId = rgTemperature.checkedRadioButtonId
        if (checkedTemperatureRadioButtonId != -1){
            Log.d("LCFR-Debug","SettingsFragments - onSaveClicled - " +
                    "return " +  rgTemperature.checkedRadioButtonId )
            when(checkedTemperatureRadioButtonId){
                rb_celsius.id -> temperatureUnit = "C"
                rb_fahrenheit.id -> temperatureUnit = "F"
            }

        } else {
            //todo: implement toast
            Log.d("LCFR-Debug","SettingsFragments - onSaveClicked - " +
                    "return Toast" )
            return
        }

        val checkedLanguageRadioButtonId = rgLanguage.checkedRadioButtonId
        if (checkedLanguageRadioButtonId != -1){
            Log.d("LCFR-Debug","SettingsFragments - onSaveClicked - " +
                    "return " +  rgLanguage.checkedRadioButtonId )
            when(checkedLanguageRadioButtonId){
                rb_english.id -> language = "EN"
                rb_portuguese.id -> language = "PT"
            }

        } else {
            //todo: implement toast
            Toast.makeText(view.context, "You have chose the language preference", Toast.LENGTH_SHORT).show()

            Log.d("LCFR-Debug","SettingsFragments - onSaveClicked - " +
                    "return Toast" )
            return
        }

        savePrefs()
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

    private fun setRadioButton(){
        when(language){
            "EN" -> rb_english.isChecked = true
            "PT" -> rb_portuguese.isChecked = true
        }

        when(temperatureUnit){
            "C" -> rb_celsius.isChecked = true
            "F" -> rb_fahrenheit.isChecked = true
        }
    }


}