package com.lucaramos.howistheweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucaramos.howistheweather.R
import com.lucaramos.howistheweather.adapters.FavoriteAdapter
import com.lucaramos.howistheweather.database.AppDatabase
import com.lucaramos.howistheweather.model.CityDatabase
import kotlinx.android.synthetic.main.fragment_favorites.*


class FavoriteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val favoriteContainer = inflater.inflate(R.layout.fragment_favorites, container, false)

        return favoriteContainer
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = context?.let { AppDatabase.getInstance(it) }
        val list = db?.cityDatabaseDao()?.getAllCityDatabase()

        rv_favorite.adapter = FavoriteAdapter(list)
        rv_favorite.layoutManager = LinearLayoutManager(context)
        rv_favorite.addItemDecoration(FavoriteAdapter.FavoriteItemDecoration(25))
    }
    fun fetchFavoriteCities() : List<CityDatabase>?{

        return null
    }
}