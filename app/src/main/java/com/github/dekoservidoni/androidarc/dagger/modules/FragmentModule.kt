package com.github.dekoservidoni.androidarc.dagger.modules

import com.github.dekoservidoni.androidarc.view.fragments.FavoriteFragment
import com.github.dekoservidoni.androidarc.view.fragments.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun provideSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun provideFavoriteFragment(): FavoriteFragment
}