package com.github.dekoservidoni.androidarc.view.adapters.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.github.dekoservidoni.androidarc.R


class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var drinkName: TextView? = null
    var drinkImage: ImageView? = null

    init {
        drinkName = view.findViewById(R.id.drink_label)
        drinkImage = view.findViewById(R.id.drink_image)
    }

}