package com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.cache

import com.github.aleksandrgrebenkin.poplibapp.mvp.model.cache.ITeamPlayersCache
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.Team
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.TeamPlayer
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.RoomTeamPlayer
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.database.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.lang.RuntimeException

class RoomTeamPlayersCache(val db: Database) : ITeamPlayersCache {
    override fun putTeamPlayers(team: Team, teamPlayers: List<TeamPlayer>) =
        Completable.fromAction {
            val roomTeam = team.name.let {
                db.teamDao.findByName(it)
            } ?: throw RuntimeException("No such team in cache")
            val roomTeamPlayers = teamPlayers.map { teamPlayer ->
                RoomTeamPlayer(
                    teamPlayer.accountId,
                    teamPlayer.name ?: "unknown",
                    teamPlayer.gamesPlayed,
                    teamPlayer.wins,
                    teamPlayer.isCurrentTeamPlayer ?: false,
                    roomTeam.teamId
                )
            }
            db.teamPlayerDao.insert(roomTeamPlayers)
        }

    override fun getTeamPlayers(team: Team) = Single.fromCallable {
        val roomTeam = team.name.let { db.teamDao.findByName(it) }
            ?: throw RuntimeException("No such team in cache")
        db.teamPlayerDao.findForTeam(roomTeam.teamId)
            .map { roomTeamPlayer ->
                TeamPlayer(
                    roomTeamPlayer.accountId,
                    roomTeamPlayer.name,
                    roomTeamPlayer.gamesPlayed,
                    roomTeamPlayer.wins,
                    roomTeamPlayer.isCurrentTeamPlayer
                )
            }
    }
}