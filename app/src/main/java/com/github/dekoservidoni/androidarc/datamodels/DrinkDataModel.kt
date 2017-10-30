package com.github.dekoservidoni.androidarc.datamodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.github.dekoservidoni.androidarc.datamodels.database.DrinkDao
import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import com.github.dekoservidoni.androidarc.datamodels.models.Resource
import com.github.dekoservidoni.androidarc.datamodels.models.ResponseDrink
import com.github.dekoservidoni.androidarc.datamodels.network.NetworkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class DrinkDataModel @Inject constructor() {

    @Inject
    lateinit var drinkDao: DrinkDao

    /// Network

    fun getDataFromAPI(term: String): LiveData<Resource<ResponseDrink>> {
        val data = MutableLiveData<Resource<ResponseDrink>>()

        Resource.loading<List<Drink>>()

        NetworkClient.getServices()?.searchDrinks(term)?.enqueue(object : Callback<ResponseDrink> {
            override fun onResponse(call: Call<ResponseDrink>, response: Response<ResponseDrink>) {
                val callResponse = response.body()

                if(callResponse?.drinks == null
                        || callResponse.drinks.isEmpty()) {
                    data.value = Resource.error("Your search returned no results! :(", null)
                } else {
                    data.value = Resource.success(callResponse)
                }
            }

            override fun onFailure(call: Call<ResponseDrink>, t: Throwable) {
                data.value = Resource.error<ResponseDrink>(t.localizedMessage, null)
            }
        })

        return data
    }

    /// Database

    fun getDataFromDatabase(): LiveData<Resource<ResponseDrink>> {
        val data = MutableLiveData<Resource<ResponseDrink>>()

        Transformations.map(drinkDao.getAll(), {
            it?.let {
                if(it.isEmpty()) {
                    data.value = Resource.error("You have no favorites yet!", null)
                } else {
                    data.value = Resource.success(ResponseDrink(it))
                }
            }
        })

        return data
    }

    fun addFavorite(drink: Drink) {
        drinkDao.insert(drink)
    }

    fun removeFavorite(drink:Drink) {
        drinkDao.delete(drink)
    }
}