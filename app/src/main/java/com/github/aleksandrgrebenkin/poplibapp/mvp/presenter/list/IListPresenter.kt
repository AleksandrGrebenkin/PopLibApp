package com.github.aleksandrgrebenkin.poplibapp.mvp.presenter.list

import com.github.aleksandrgrebenkin.poplibapp.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}