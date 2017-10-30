package com.github.dekoservidoni.androidarc.dagger

import android.app.Application
import com.github.dekoservidoni.androidarc.BaseApp
import com.github.dekoservidoni.androidarc.dagger.modules.ActivityModule
import com.github.dekoservidoni.androidarc.dagger.modules.RoomModule
import com.github.dekoservidoni.androidarc.dagger.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,
            AppModule::class,
            ActivityModule::class,
            RoomModule::class,
            ViewModelModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: BaseApp)
}