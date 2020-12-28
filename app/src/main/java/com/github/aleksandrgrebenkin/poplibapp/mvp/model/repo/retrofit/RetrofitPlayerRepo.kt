package com.github.aleksandrgrebenkin.poplibapp.mvp.model.repo.retrofit

import com.github.aleksandrgrebenkin.poplibapp.mvp.model.api.IDotaSource
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.cache.ITeamPlayersCache
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.Team
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.network.INetworkStatus
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.repo.IPlayerRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitPlayerRepo(
    val api: IDotaSource,
    val networkStatus: INetworkStatus,
    val cache: ITeamPlayersCache
) : IPlayerRepo {
    override fun getTeamPlayers(team: Team) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                team.teamId.let { teamId ->
                    api.getTeamPlayers(teamId).flatMap { teamplayers ->
                        cache.putTeamPlayers(team, teamplayers).andThen(Single.just(teamplayers))
                    }
                }
            } else {
                cache.getTeamPlayers(team)
            }
        }.subscribeOn(Schedulers.io())
}