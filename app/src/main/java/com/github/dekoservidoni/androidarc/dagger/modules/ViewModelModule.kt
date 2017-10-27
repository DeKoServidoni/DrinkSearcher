package com.github.dekoservidoni.androidarc.dagger.modules

import dagger.Module
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.dekoservidoni.androidarc.dagger.AppViewModelFactory
import com.github.dekoservidoni.androidarc.dagger.ViewModelKey
import com.github.dekoservidoni.androidarc.viewmodels.SearchViewModel
import dagger.multibindings.IntoMap
import dagger.Binds


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun bindDrinkViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    internal abstract fun bindAppViewModelFactory(appViewModelFactory: AppViewModelFactory): ViewModelProvider.Factory
}