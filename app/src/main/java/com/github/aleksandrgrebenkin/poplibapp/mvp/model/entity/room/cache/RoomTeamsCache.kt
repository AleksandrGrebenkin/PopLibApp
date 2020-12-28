package com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.cache

import com.github.aleksandrgrebenkin.poplibapp.mvp.model.cache.ITeamsCache
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.Team
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.RoomTeam
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.database.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomTeamsCache(val db: Database) : ITeamsCache {
    override fun putTeams(teams: List<Team>) = Completable.fromAction {
        val roomTeams = teams.map { team ->
            RoomTeam(
                team.teamId,
                team.name,
                team.tag,
                team.wins,
                team.losses,
                team.rating,
                team.logoUrl ?: ""
            )
        }
        db.teamDao.insert(roomTeams)
    }

    override fun getTeams() = Single.fromCallable {
        db.teamDao.getAll().map { roomTeam ->
            Team(
                roomTeam.teamId,
                roomTeam.name,
                roomTeam.tag,
                roomTeam.wins,
                roomTeam.losses,
                roomTeam.rating,
                roomTeam.logoUrl
            )
        }
    }
}