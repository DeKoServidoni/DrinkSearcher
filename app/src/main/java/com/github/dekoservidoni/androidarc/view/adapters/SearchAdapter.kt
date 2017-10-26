package com.github.dekoservidoni.androidarc.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.github.dekoservidoni.androidarc.R
import com.github.dekoservidoni.androidarc.view.adapters.viewholders.SearchViewHolder
import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import java.util.*

class SearchAdapter: RecyclerView.Adapter<SearchViewHolder>() {

    private val content = ArrayList<Drink>()

    override fun onBindViewHolder(holder: SearchViewHolder?, position: Int) {
        val drink = content[position]

        val rowLabel = "%s\n%s".format(Locale.getDefault(), drink.name, drink.iba)
        holder?.drinkName?.text = rowLabel

        holder?.drinkImage.let {
            Glide.with(holder?.drinkImage?.context)
                    .load(drink.thumb)
                    .into(holder?.drinkImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchViewHolder {
        val rootView = LayoutInflater.from(parent?.context).inflate(R.layout.row_search, parent, false)
        return SearchViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return content.size
    }

    /// Public methods

    fun updateContent(newData: List<Drink>) {
        content.clear()
        content.addAll(newData)
        notifyDataSetChanged()
    }

    fun clearContent() {
        content.clear()
        notifyDataSetChanged()
    }
}