package com.github.dekoservidoni.androidarc.datamodels.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.reactivex.annotations.NonNull

@Entity(tableName = "drinks")
data class Drink(

        @NonNull
        @PrimaryKey
        @ColumnInfo(name = "drink_id")
        @SerializedName("idDrink")
        var id: String = "",

        @ColumnInfo(name = "drink_name")
        @SerializedName("strDrink")
        var name: String? = null,

        @ColumnInfo(name = "drink_video")
        @SerializedName("strVideo")
        var video: String? = null,

        @ColumnInfo(name = "drink_category")
        @SerializedName("strCategory")
        var category: String? = null,

        @ColumnInfo(name = "drink_iba")
        @SerializedName("strIBA")
        var iba: String? = null,

        @ColumnInfo(name = "drink_alcoholic")
        @SerializedName("strAlcoholic")
        var alcoholic: String? = null,

        @ColumnInfo(name = "drink_glass")
        @SerializedName("strGlass")
        var glass: String? = null,

        @ColumnInfo(name = "drink_instructions")
        @SerializedName("strInstructions")
        var instructions: String? = null,

        @ColumnInfo(name = "drink_thumb")
        @SerializedName("strDrinkThumb")
        var thumb: String? = null)