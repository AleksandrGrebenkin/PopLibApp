package com.github.aleksandrgrebenkin.poplibapp.mvp.model.repo.retrofit

import com.github.aleksandrgrebenkin.poplibapp.mvp.model.api.IDotaSource
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.cache.ITeamsCache
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.network.INetworkStatus
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.repo.ITeamsRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitTeamsRepo(
    val api: IDotaSource,
    val networkStatus: INetworkStatus,
    val cache: ITeamsCache
) : ITeamsRepo {
    override fun getTeams() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getTeams().flatMap { teams ->
                cache.putTeams(teams).andThen(Single.just(teams))
            }
        } else {
            cache.getTeams()
        }
    }.subscribeOn(Schedulers.io())
}