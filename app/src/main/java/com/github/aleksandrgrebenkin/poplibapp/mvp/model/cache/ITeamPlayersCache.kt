package com.github.aleksandrgrebenkin.poplibapp.mvp.model.cache

import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.Team
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.TeamPlayer
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ITeamPlayersCache {
    fun putTeamPlayers(team: Team,teamPlayers: List<TeamPlayer>): Completable
    fun getTeamPlayers(team: Team): Single<List<TeamPlayer>>
}