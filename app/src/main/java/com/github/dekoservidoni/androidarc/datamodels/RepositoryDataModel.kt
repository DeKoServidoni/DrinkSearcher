package com.github.dekoservidoni.androidarc.datamodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import com.github.dekoservidoni.androidarc.datamodels.models.Resource
import com.github.dekoservidoni.androidarc.datamodels.models.ResponseDrink
import com.github.dekoservidoni.androidarc.datamodels.network.NetworkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class RepositoryDataModel @Inject constructor() {

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

    fun getDataFromDatabase() {
        //TODO: Implement with ROOM
    }
}