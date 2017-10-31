package com.github.dekoservidoni.androidarc.dagger

import com.github.dekoservidoni.androidarc.BaseApp
import com.github.dekoservidoni.androidarc.dagger.modules.ActivityModule
import com.github.dekoservidoni.androidarc.dagger.modules.DatabaseModule
import com.github.dekoservidoni.androidarc.dagger.modules.NetworkModule
import com.github.dekoservidoni.androidarc.dagger.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        ViewModelModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(app: BaseApp): Builder
        fun build(): AppComponent
    }

    fun inject(application: BaseApp)
}