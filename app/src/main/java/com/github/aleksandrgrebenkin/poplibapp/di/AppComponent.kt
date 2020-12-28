package com.github.aleksandrgrebenkin.poplibapp.di

import com.github.aleksandrgrebenkin.poplibapp.di.modules.*
import com.github.aleksandrgrebenkin.poplibapp.mvp.presenter.MainPresenter
import com.github.aleksandrgrebenkin.poplibapp.mvp.presenter.TeamPlayersPresenter
import com.github.aleksandrgrebenkin.poplibapp.mvp.presenter.TeamsPresenter
import com.github.aleksandrgrebenkin.poplibapp.ui.activity.MainActivity
import com.github.aleksandrgrebenkin.poplibapp.ui.adapter.TeamsRVAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        ImageModule::class,
        NavigationModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(teamsRVAdapter: TeamsRVAdapter)

    fun inject(mainPresenter: MainPresenter)
    fun inject(teamsPresenter: TeamsPresenter)
    fun inject(teamPlayersPresenter: TeamPlayersPresenter)
}