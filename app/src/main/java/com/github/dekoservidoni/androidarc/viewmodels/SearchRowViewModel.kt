package com.github.dekoservidoni.androidarc.viewmodels

import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import java.util.*

class SearchRowViewModel(var drink: Drink) : BaseViewModel() {

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
}