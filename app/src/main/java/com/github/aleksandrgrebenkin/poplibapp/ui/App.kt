package com.github.aleksandrgrebenkin.poplibapp.ui

import android.app.Application
import com.github.aleksandrgrebenkin.poplibapp.di.AppComponent
import com.github.aleksandrgrebenkin.poplibapp.di.DaggerAppComponent
import com.github.aleksandrgrebenkin.poplibapp.di.modules.AppModule

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
