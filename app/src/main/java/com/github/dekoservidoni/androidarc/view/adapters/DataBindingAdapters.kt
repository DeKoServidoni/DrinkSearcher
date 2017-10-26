package com.github.dekoservidoni.androidarc.view.adapters

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.dekoservidoni.androidarc.datamodels.models.Drink

object DataBindingAdapters {

    @JvmStatic
    @BindingAdapter("src")
    fun setImageResource(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
                .load(url)
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("search_list")
    fun setContentList(recyclerView: RecyclerView, newContent: List<Drink>) {
        val adapter = recyclerView.adapter as SearchAdapter
        adapter.updateContent(newContent)
    }
}