package com.github.dekoservidoni.androidarc.viewmodels

import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import java.util.*

class FavoriteRowViewModel(private var drink: Drink) : BaseViewModel() {

    fun getRowDescription(): String {
        return "%s\n%s - %s".format(Locale.getDefault(), drink.name, drink.category, drink.glass)
    }

    fun getRowImage(): String {
        drink.thumb?.let {
            return it
        }

        return ""
    }
}