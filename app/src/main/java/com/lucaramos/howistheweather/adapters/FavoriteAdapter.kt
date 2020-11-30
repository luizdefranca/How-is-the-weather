package com.lucaramos.howistheweather.adapters

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucaramos.howistheweather.R
import com.lucaramos.howistheweather.extensions.inflate
import com.lucaramos.howistheweather.model.City
import com.lucaramos.howistheweather.model.CityDatabase
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class FavoriteAdapter(val list: MutableList<CityDatabase>?) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private val ICON_ADDRESS = "http://openweathermap.org/img/w/"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = parent.inflate(R.layout.recyclerview_item, false)
        return FavoriteViewHolder(inflatedView)

    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        when (holder){

            is FavoriteAdapter.FavoriteViewHolder -> {
                if (position < (list?.size ?: 0)){
                    val element = list?.get(position)
                    if (element != null){
                        holder.bindView(element)
                    }
                }
            }
        }
    }

    fun addItem(list : MutableList<CityDatabase>) {
        if (list != null){
            list!!.addAll(list)
        }
    }
    class FavoriteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tvCityName = itemView.tv_city
        val tvId = itemView.tv_id

        fun bindView(city: CityDatabase){
            tvCityName.text = city.cityName
            tvId.text = city.id.toString()
        }

    }

    class FavoriteItemDecoration (val spaceHeight: Int): RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            
            with (outRect){
                if (parent.getChildAdapterPosition(view) == 0){
                    top = spaceHeight
                }
                left = spaceHeight
                right = spaceHeight
                bottom = spaceHeight
            }

        }
    }



}