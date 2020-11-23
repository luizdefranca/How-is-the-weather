package com.lucaramos.howistheweather.ui

import android.annotation.SuppressLint
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
import com.lucaramos.howistheweather.R
import kotlinx.android.synthetic.main.fragment_search.*
import okhttp3.Request
import okhttp3.RequestBody
import java.net.URL

class SearchFragment : Fragment(), View.OnClickListener  {

    private val API_KEY  = "dfd1a75a5e349387b02c6505d726a5c7"
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

    override fun onClick(v: View?) {

        when(view?.context?.let { isConnectivityAvailable(it) }){
            true -> Toast.makeText(v?.context, getString(R.string.message_toast_online), Toast.LENGTH_LONG).show()
            false -> Toast.makeText(v?.context, getString(R.string.message_toast_offline), Toast.LENGTH_LONG).show()
        }
        teste()
    }

    fun teste(){
        val base = URL("http://api.openweathermap.org/data/2.5/weather")
        val url: URL = URL( base, "text")
        url.let {

        }

        Log.d("LCFR","${url.toString()}" )
    }
}

