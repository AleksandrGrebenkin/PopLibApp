package com.github.aleksandrgrebenkin.poplibapp.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface TeamPlayersView : MvpView {
    fun init()
    fun updateList()
    fun showError(text: String)
}