package com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.database

import androidx.room.RoomDatabase
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.RoomTeam
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.RoomTeamPlayer
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.dao.TeamDao
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.dao.TeamPlayerDao

@androidx.room.Database(
    entities = [RoomTeam::class, RoomTeamPlayer::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val teamDao: TeamDao
    abstract val teamPlayerDao: TeamPlayerDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null
    }
}