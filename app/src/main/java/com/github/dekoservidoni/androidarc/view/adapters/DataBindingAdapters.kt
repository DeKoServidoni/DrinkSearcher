package com.github.dekoservidoni.androidarc.view.adapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
    @BindingAdapter("favorite_icon")
    fun setFavoriteIcon(imageView: ImageView, resource:Int) {
        imageView.setImageResource(resource)
    }
}