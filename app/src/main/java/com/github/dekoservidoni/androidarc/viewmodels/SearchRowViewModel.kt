package com.github.dekoservidoni.androidarc.viewmodels

import android.databinding.Bindable
import com.github.dekoservidoni.androidarc.BR
import com.github.dekoservidoni.androidarc.R
import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import java.util.*

class SearchRowViewModel(var drink: Drink, favorites: ArrayList<String>) : BaseViewModel() {

    init {
        drink.isFavorite = favorites.contains(drink.id)
    }

    fun getRowDescription(): String {
        drink.iba?.let {
            return "%s\n%s".format(Locale.getDefault(), drink.name, drink.iba)
        }

        return "%s".format(Locale.getDefault(), drink.name)
    }

    fun getRowImage(): String {
        drink.thumb?.let {
            return it
        }

        return ""
    }

    @Bindable
    fun getFavoriteIcon(): Int {
        return if(drink.isFavorite) R.drawable.ic_favorited else R.drawable.ic_favorite_add
    }

    fun favoriteDrink(favorite: Boolean) {
        drink.isFavorite = favorite
        notifyPropertyChanged(BR.favoriteIcon)
    }
}