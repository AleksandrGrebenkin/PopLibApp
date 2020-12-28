package com.github.aleksandrgrebenkin.poplibapp.di.modules

import android.widget.ImageView
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.image.IImageLoader
import com.github.aleksandrgrebenkin.poplibapp.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}