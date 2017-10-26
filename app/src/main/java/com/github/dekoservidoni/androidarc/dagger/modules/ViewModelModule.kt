package com.github.dekoservidoni.androidarc.dagger.modules

import dagger.Module
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.dekoservidoni.androidarc.dagger.AppViewModelFactory
import com.github.dekoservidoni.androidarc.dagger.ViewModelKey
import com.github.dekoservidoni.androidarc.viewmodels.DrinkViewModel
import dagger.multibindings.IntoMap
import dagger.Binds


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DrinkViewModel::class)
    internal abstract fun bindDrinkViewModel(drinkViewModel: DrinkViewModel): ViewModel

    @Binds
    internal abstract fun bindAppViewModelFactory(appViewModelFactory: AppViewModelFactory): ViewModelProvider.Factory
}