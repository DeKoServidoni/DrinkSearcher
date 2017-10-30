package com.github.dekoservidoni.androidarc.datamodels.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.github.dekoservidoni.androidarc.datamodels.models.Drink

@Database(entities = arrayOf(Drink::class), version = 1)
abstract class DatabaseClient: RoomDatabase() {
    abstract fun drinkDAO(): DrinkDao
}