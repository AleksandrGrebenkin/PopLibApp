package com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["teamId", "accountId"],
    foreignKeys = [ForeignKey(
        entity = RoomTeam::class,
        parentColumns = ["teamId"],
        childColumns = ["teamId"],
        onDelete = ForeignKey.NO_ACTION
    )]
)
class RoomTeamPlayer(
    var accountId: String,
    var name: String,
    var gamesPlayed: Int,
    var wins: Int,
    var isCurrentTeamPlayer: Boolean,
    var teamId: Int
)