package com.github.dekoservidoni.androidarc.dagger.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.github.dekoservidoni.androidarc.datamodels.database.DatabaseClient
import com.github.dekoservidoni.androidarc.datamodels.database.DrinkDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): DatabaseClient {
        return Room.databaseBuilder(application, DatabaseClient::class.java, "drinks-db").build()
    }

    @Singleton
    @Provides
    fun provideDrinkDao(databaseClient: DatabaseClient): DrinkDao {
        return databaseClient.drinkDAO()
    }
}