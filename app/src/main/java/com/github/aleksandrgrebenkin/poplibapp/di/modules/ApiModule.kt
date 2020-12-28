package com.github.aleksandrgrebenkin.poplibapp.di.modules

import com.github.aleksandrgrebenkin.poplibapp.mvp.model.api.IDotaSource
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.network.INetworkStatus
import com.github.aleksandrgrebenkin.poplibapp.ui.App
import com.github.aleksandrgrebenkin.poplibapp.ui.network.AndroidNetworkStatus
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl() = "https://api.opendota.com/api/"

    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IDotaSource =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDotaSource::class.java)

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)
}