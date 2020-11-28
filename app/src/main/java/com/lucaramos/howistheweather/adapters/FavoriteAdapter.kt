package com.lucaramos.howistheweather.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucaramos.howistheweather.R
import com.lucaramos.howistheweather.model.City
import com.lucaramos.howistheweather.model.CityDatabase
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class FavoriteAdapter(val list: List<CityDatabase>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private val ICON_ADDRESS = "http://openweathermap.org/img/w/"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        return FavoriteViewHolder(inflater.inflate(R.layout.recyclerview_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
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

    inner class FavoriteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tvCityName = itemView.tv_city
        val tvId = itemView.tv_id


        @SuppressLint("ResourceType")
        fun bindView(city: CityDatabase) = with(itemView) {

            tvCityName.text = city.cityName
            tvId.text = city.id.toString()
/*
            Glide.with(context)
                .load("$ICON_ADDRESS${city.weather[0].icon}@4x.png")
//               .placeholder(R.mipmap.ic_weather_placeholder)
                .placeholder(R.raw.raw_image_holder)
                .error(R.mipmap.ic_weather_placeholder)
//               .transform(BlurTransformation())
//               .listener( RequestListener<Drawable>(){
//
//               })
                .circleCrop()

                .into(iv_weather)

            */
        }

    }

}