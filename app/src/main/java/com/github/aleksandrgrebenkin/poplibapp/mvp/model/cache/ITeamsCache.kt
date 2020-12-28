package com.github.aleksandrgrebenkin.poplibapp.mvp.model.cache

import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.Team
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ITeamsCache {
    fun putTeams(teams: List<Team>): Completable
    fun getTeams(): Single<List<Team>>
}