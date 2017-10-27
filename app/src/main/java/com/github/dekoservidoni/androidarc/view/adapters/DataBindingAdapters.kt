package com.github.dekoservidoni.androidarc.view.adapters

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import de.hdodenhof.circleimageview.CircleImageView

object DataBindingAdapters {

    @JvmStatic
    @BindingAdapter("src")
    fun setImageResource(imageView: CircleImageView, url: String) {
        Glide.with(imageView.context)
                .load(url)
                .apply(RequestOptions.centerInsideTransform())
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("search_list")
    fun setContentList(recyclerView: RecyclerView, newContent: List<Drink>) {
        val adapter = recyclerView.adapter as SearchAdapter
        adapter.updateContent(newContent)
    }
}