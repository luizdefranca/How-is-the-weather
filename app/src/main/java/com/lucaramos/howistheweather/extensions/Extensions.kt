package com.lucaramos.howistheweather.extensions

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.lucaramos.howistheweather.R

fun String.isTrimEmpty() = this.trim().isEmpty()

/*

fun String[].tags (view View) {
    getResources().getStringArray(R.array.tags);
for(String tag : tags)
    String[] pair = tag . split (":");

    String key = pair [0];
    String value = pair [1];
}*/
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun RecyclerView.ItemDecoration.setOffsets(spaceHeight: Int){

    fun getItemOffsets(
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