package com.github.aleksandrgrebenkin.poplibapp.mvp.view.list

interface TeamPlayerItemView : IItemView {
    fun setName(text: String)
    fun setGamesPlayed(text: String)
    fun setWins(text: String)
}