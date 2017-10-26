package com.github.dekoservidoni.androidarc.datamodels.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {

    companion object {

        private val BASE_URL = "http://www.thecocktaildb.com/api/json/v1/1/"
        private var service: NetworkApi? = null

        fun getServices(): NetworkApi? {
            if(service == null) {
                buildServices()
            }

            return service
        }

        private fun buildServices() {

            service = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(NetworkApi::class.java)
        }
    }
}
