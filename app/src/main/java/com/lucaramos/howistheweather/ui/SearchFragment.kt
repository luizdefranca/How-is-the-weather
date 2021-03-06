package com.lucaramos.howistheweather.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.lucaramos.howistheweather.R
import com.lucaramos.howistheweather.adapters.MyAdapter
import com.lucaramos.howistheweather.database.AppDatabase
import com.lucaramos.howistheweather.extensions.hideKeyboard
import com.lucaramos.howistheweather.manager.ApiManager
import com.lucaramos.howistheweather.model.City
import com.lucaramos.howistheweather.model.CityDatabase
import com.lucaramos.howistheweather.model.Root
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL


class SearchFragment : Fragment(), View.OnClickListener  {

    private val API_KEY  = "f203e8ac18f8a301e358f13f7576bea1"
    private val ICON_ADDRESS = "http://openweathermap.org/img/w/04n.png"
    private val API_URL = "http://api.openweathermap.org/data/2.5/weather"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val searchContainer = inflater.inflate(R.layout.fragment_search, container, false)
        return searchContainer
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_search.setOnClickListener(this)
        fab_favorite.setOnClickListener(this)
        rv_search.adapter = MyAdapter(mutableListOf())
    }

    override fun onClick(v: View?) {
        when (v?.id){
            btn_search.id -> {
                fetchCities(v)
            }
            fab_favorite.id -> {
                saveFavorite(v)
            }
        }
    }

    @SuppressLint("WrongConstant")
    fun isConnectivityAvailable(content: Context): Boolean {
        val con = context?.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val result = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> con.getNetworkCapabilities(con.activeNetwork)
                ?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)

                }
            else -> {
                con.activeNetworkInfo!!.run {
                    type == ConnectivityManager.TYPE_MOBILE || type == ConnectivityManager.TYPE_WIFI
                }
            }
        }
        return result ?: false
    }

    private fun saveFavorite(v: View) {
        val city = et_search.text.toString()
        val service = ApiManager().apiService()
        val call = service.getCityWeather(city)
        call.enqueue(object : Callback<City> {
            override fun onResponse(call: Call<City>, response: Response<City>) {
                when (response.isSuccessful) {
                    true -> {
                        val city = response.body()
                        if (context != null) {
                            val db = AppDatabase.getInstance(context!!)
                            val cityDatabase = CityDatabase(city!!.id, city!!.name)
                            var result = db?.cityDatabaseDao()?.save(cityDatabase)

                            Toast.makeText(context, "Result: $result", Toast.LENGTH_LONG).show()
                        }
                    }
                    else -> {
                        Log.d("LCFR", "Response is not success: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<City>, t: Throwable) {
                Log.d("LCFR", "Response is not success: ${t.message}")
            }
        })
    }

    private fun fetchCities(v: View?) {
        when (view?.context?.let { isConnectivityAvailable(it) }) {
            true -> {
                pb_search.visibility = View.VISIBLE
                Toast.makeText(
                    v?.context,
                    getString(R.string.message_toast_online),
                    Toast.LENGTH_LONG
                ).show()

                val city = et_search.text.toString()
                val service = ApiManager().apiService()
                val call = service.findTemperature(city, API_KEY)
                call.enqueue(object : Callback<Root> {
                    override fun onFailure(call: Call<Root>, t: Throwable) {
                        Log.d("LCFR", "Error Message - onFailure: ${t.localizedMessage}")
                        pb_search.visibility = View.GONE
                    }

                    override fun onResponse(call: Call<Root>, response: Response<Root>) {
                        when (response.isSuccessful) {
                            true -> {
                                val root = response.body()
                                Log.d("LCFR", "The city is ${root?.list.toString() ?: "null"}")

                                val cities = mutableListOf<City>()
                                root?.list?.forEach {
                                    cities.add(it)
                                }
                                (rv_search.adapter as MyAdapter).addItems(cities)
                                rv_search.layoutManager = LinearLayoutManager(context)
                                rv_search.addItemDecoration(MyAdapter.MyItemDecoration(30))
                            }
                            false -> {
                                Log.d("LCFR", "Response is not success: ${response.message()}")
                            }
                        }
                        pb_search.visibility = View.GONE
                    }
                })
            }
            false -> Toast.makeText(
                v?.context,
                getString(R.string.message_toast_offline),
                Toast.LENGTH_LONG
            ).show()
        }
        view?.hideKeyboard()
    }

    fun teste(){
        val base = URL("http://api.openweathermap.org/data/2.5/weather")
        val url: URL = URL( base, "text")
        Log.d("LCFR","${url.toString()}" )
    }


}

