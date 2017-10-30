package com.github.dekoservidoni.androidarc.dagger

import android.content.Context
import com.github.dekoservidoni.androidarc.BaseApp
import com.github.dekoservidoni.androidarc.datamodels.DrinkDataModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(app: BaseApp): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun provideDrinkDataModel(): DrinkDataModel {
        return DrinkDataModel()
    }
}