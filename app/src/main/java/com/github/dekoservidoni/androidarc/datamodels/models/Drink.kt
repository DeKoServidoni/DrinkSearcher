package com.github.dekoservidoni.androidarc.datamodels.models

import com.google.gson.annotations.SerializedName

data class Drink(@SerializedName("idDrink")
                 var id: String? = null,

                 @SerializedName("strDrink")
                 var name: String? = null,

                 @SerializedName("strVideo")
                 var video: String? = null,

                 @SerializedName("strCategory")
                 var category: String? = null,

                 @SerializedName("strIBA")
                 var iba: String? = null,

                 @SerializedName("strAlcoholic")
                 var alcoholic: String? = null,

                 @SerializedName("strGlass")
                 var glass: String? = null,

                 @SerializedName("strInstructions")
                 var instructions: String? = null,

                 @SerializedName("strDrinkThumb")
                 var thumb: String? = null)