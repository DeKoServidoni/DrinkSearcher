package com.github.dekoservidoni.androidarc.dagger.modules

import android.arch.persistence.room.Room
import com.github.dekoservidoni.androidarc.BaseApp
import com.github.dekoservidoni.androidarc.datamodels.database.DatabaseClient
import com.github.dekoservidoni.androidarc.datamodels.database.DrinkDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides @Singleton
    fun provideDatabase(application: BaseApp): DatabaseClient {
        return Room.databaseBuilder(application, DatabaseClient::class.java, "drinks-db")
                .build()
    }

    @Provides @Singleton
    fun provideDrinkDao(databaseClient: DatabaseClient): DrinkDao {
        return databaseClient.drinkDAO()
    }
}