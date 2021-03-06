package com.github.aleksandrgrebenkin.poplibapp.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}