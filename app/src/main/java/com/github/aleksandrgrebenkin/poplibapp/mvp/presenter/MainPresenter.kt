package com.github.aleksandrgrebenkin.poplibapp.mvp.presenter

import com.github.aleksandrgrebenkin.poplibapp.mvp.view.MainView
import com.github.aleksandrgrebenkin.poplibapp.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.TeamsScreen())
    }

    fun backPressed() {
        router.exit()
    }
}