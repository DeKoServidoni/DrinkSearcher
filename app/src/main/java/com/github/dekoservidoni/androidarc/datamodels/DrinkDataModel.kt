package com.github.dekoservidoni.androidarc.datamodels

import android.arch.lifecycle.LiveData
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


class DrinkDataModel @Inject constructor(var drinkDao: DrinkDao, var networkClient: NetworkClient) {

    /// Network

    fun getDataFromAPI(term: String): LiveData<Resource<ResponseDrink>> {
        val data = MutableLiveData<Resource<ResponseDrink>>()

        Resource.loading<List<Drink>>()

        networkClient.searchDrinks(term).enqueue(object : Callback<ResponseDrink> {
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

    /// DatabaseClient

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
//        repository.drinkDao.insert(drink)
    }

    fun removeFavorite(drink:Drink) {
//        repository.drinkDao.delete(drink)
    }
}