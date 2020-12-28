package com.github.aleksandrgrebenkin.poplibapp.mvp.model.repo

import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.Team
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.TeamPlayer
import io.reactivex.rxjava3.core.Single

interface ITeamsRepo {
    fun getTeams(): Single<List<Team>>
}