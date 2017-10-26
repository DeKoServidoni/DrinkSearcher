package com.github.dekoservidoni.androidarc.datamodels.models

import com.google.gson.annotations.SerializedName

data class ResponseDrink(@SerializedName("drinks")
                         var drinks: List<Drink> = ArrayList())