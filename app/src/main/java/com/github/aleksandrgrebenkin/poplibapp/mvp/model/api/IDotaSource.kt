package com.github.aleksandrgrebenkin.poplibapp.mvp.model.api

import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.Team
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.TeamPlayer
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IDotaSource {

    @GET("teams")
    fun getTeams(): Single<List<Team>>

    @GET("teams/{team_id}/players")
    fun getTeamPlayers(@Path("team_id") teamId: Int): Single<List<TeamPlayer>>
}