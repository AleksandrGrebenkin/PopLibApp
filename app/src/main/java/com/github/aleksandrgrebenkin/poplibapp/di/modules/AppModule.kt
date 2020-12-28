package com.github.aleksandrgrebenkin.poplibapp.di.modules

import com.github.aleksandrgrebenkin.poplibapp.ui.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App = app

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}