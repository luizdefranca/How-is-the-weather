package com.lucaramos.howistheweather.adapters

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.lucaramos.howistheweather.R
import com.lucaramos.howistheweather.extensions.inflate
import com.lucaramos.howistheweather.model.City
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class MyAdapter(val list: MutableList<City>?): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val ICON_ADDRESS = "http://openweathermap.org/img/w/"


    //RecyclerView Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = parent.inflate(R.layout.recyclerview_item, false)
        return MyViewHolder(inflatedView)
    }


    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        when (holder){

            is MyViewHolder -> {
                if (position < (list?.size ?: 0)){
                    val element = list?.get(position)
                    if (element != null){
                        holder.bindView(element)
                    }
                }
            }
        }
    }

    fun addItems(cities: MutableList<City>) {
        if(list != null){
            list!!.addAll(cities)

        }

    }


    /**
     * Inner class MyViewHolder
     * ViewHolder
     */

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tvCityName = itemView.tv_city
        val tvId = itemView.tv_id


        @SuppressLint("ResourceType")
        fun bindView(city: City) = with(itemView) {

            tvCityName.text = city.name
            tvId.text = city.id.toString()

            Glide.with(context)
                .load("$ICON_ADDRESS${city.weather[0].icon}.png")
                //               .placeholder(R.mipmap.ic_weather_placeholder)
                .placeholder(R.mipmap.ic_weather_placeholder)
                .error(R.mipmap.ic_weather_placeholder)
                //               .transform(BlurTransformation())
                //               .listener( RequestListener<Drawable>(){
                //
                //               })
                .circleCrop()
                .into(iv_weather)
        }

    }

    class MyItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration(){

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State){

            with(outRect){
                if(parent.getChildAdapterPosition(view) == 0){
                    top = spaceHeight
                }

                left = spaceHeight
                top = spaceHeight
                bottom = spaceHeight
            }

        }
    }


}