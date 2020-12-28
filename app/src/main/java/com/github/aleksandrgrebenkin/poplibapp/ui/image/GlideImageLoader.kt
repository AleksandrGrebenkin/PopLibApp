package com.github.aleksandrgrebenkin.poplibapp.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.image.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .load(url)
            .into(container)
    }
}