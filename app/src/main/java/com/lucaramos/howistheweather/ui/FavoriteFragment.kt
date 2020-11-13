package com.lucaramos.howistheweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lucaramos.howistheweather.R


class FavoriteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val favoriteContainer = inflater.inflate(R.layout.fragment_favorites, container, false)
        val textView = favoriteContainer.findViewById<TextView>(R.id.text_favorite)
        textView.text = getString(R.string.title_favorites)
        return return favoriteContainer
    }

}