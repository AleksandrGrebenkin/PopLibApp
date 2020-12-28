package com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    @Expose val teamId: Int,
    @Expose val name: String,
    @Expose val tag: String,
    @Expose val wins: Int,
    @Expose val losses: Int,
    @Expose val rating: Float,
    @Expose val logoUrl: String? = null
) : Parcelable