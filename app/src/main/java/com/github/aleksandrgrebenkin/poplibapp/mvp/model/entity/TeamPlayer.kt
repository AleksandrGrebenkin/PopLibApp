package com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamPlayer(
    @Expose val accountId: String,
    @Expose val name: String? = "unknown",
    @Expose val gamesPlayed: Int = 0,
    @Expose val wins: Int = 0,
    @Expose val isCurrentTeamPlayer: Boolean? = false
) : Parcelable