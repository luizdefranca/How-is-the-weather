package com.lucaramos.howistheweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lucaramos.howistheweather.R

class SearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val searchContainer = inflater.inflate(R.layout.fragment_search, container, false)
        val textView = searchContainer.findViewById<TextView>(R.id.et_search)
        textView.text = getString(R.string.title_search)
         return searchContainer
    }

}