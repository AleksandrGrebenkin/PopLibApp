package com.github.aleksandrgrebenkin.poplibapp.mvp.view.list

interface TeamItemView : IItemView {
    fun setName(text: String)
    fun setTag(text: String)
    fun setRating(text: String)
    fun setWins(text: String)
    fun setLosses(text: String)
    fun loadImage(url: String)
}