package com.github.dekoservidoni.androidarc.datamodels.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.github.dekoservidoni.androidarc.datamodels.models.Drink

@Dao
interface DrinkDao {

    @Query("SELECT * FROM drinks")
    fun getAll(): LiveData<List<Drink>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(drink: Drink)

    @Delete
    fun delete(drink: Drink)
}