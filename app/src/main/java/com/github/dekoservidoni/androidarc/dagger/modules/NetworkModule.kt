package com.github.dekoservidoni.androidarc.dagger.modules

import com.github.dekoservidoni.androidarc.datamodels.network.NetworkClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides @Singleton
    fun provideNetworkClient(retrofit: Retrofit): NetworkClient {
        return retrofit.create(NetworkClient::class.java)
    }
}