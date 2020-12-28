package com.github.aleksandrgrebenkin.poplibapp.di.modules

import com.github.aleksandrgrebenkin.poplibapp.mvp.model.api.IDotaSource
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.cache.ITeamPlayersCache
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.cache.ITeamsCache
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.network.INetworkStatus
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.repo.IPlayerRepo
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.repo.ITeamsRepo
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.repo.retrofit.RetrofitPlayerRepo
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.repo.retrofit.RetrofitTeamsRepo
import com.github.aleksandrgrebenkin.poplibapp.ui.network.AndroidNetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun teamsRepo(
        api: IDotaSource,
        networkStatus: INetworkStatus,
        cache: ITeamsCache
    ): ITeamsRepo = RetrofitTeamsRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun playersRepo(
        api: IDotaSource,
        networkStatus: INetworkStatus,
        cache: ITeamPlayersCache
    ): IPlayerRepo = RetrofitPlayerRepo(api, networkStatus, cache)
}