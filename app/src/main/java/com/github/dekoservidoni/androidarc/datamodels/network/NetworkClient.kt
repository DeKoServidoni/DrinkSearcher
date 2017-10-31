package com.github.dekoservidoni.androidarc.datamodels.network


import com.github.dekoservidoni.androidarc.datamodels.models.ResponseDrink
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkClient {

    @GET("search.php")
    fun searchDrinks(@Query("s") term: String): Call<ResponseDrink>
}