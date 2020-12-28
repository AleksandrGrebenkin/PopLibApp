package com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class RoomTeam(
    @PrimaryKey var teamId: Int,
    var name: String,
    var tag: String,
    var wins: Int,
    var losses: Int,
    var rating: Float,
    var logoUrl: String? = null
)