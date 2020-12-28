package com.github.aleksandrgrebenkin.poplibapp.di.modules

import androidx.room.Room
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.cache.ITeamPlayersCache
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.cache.ITeamsCache
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.cache.RoomTeamPlayersCache
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.cache.RoomTeamsCache
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.database.Database
import com.github.aleksandrgrebenkin.poplibapp.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun teamsCache(database: Database): ITeamsCache = RoomTeamsCache(database)

    @Singleton
    @Provides
    fun teamPlayersCache(database: Database): ITeamPlayersCache = RoomTeamPlayersCache(database)
}