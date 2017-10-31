package com.github.dekoservidoni.androidarc.view.adapters.listeners

import com.github.dekoservidoni.androidarc.datamodels.models.Drink

interface DrinkActionListener {
    fun onUserAction(drink: Drink)
}