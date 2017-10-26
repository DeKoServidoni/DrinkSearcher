package com.github.dekoservidoni.androidarc.dagger.modules

import com.github.dekoservidoni.androidarc.view.activities.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = arrayOf(FragmentModule::class))
    abstract fun bindHomeActivity(): HomeActivity
}