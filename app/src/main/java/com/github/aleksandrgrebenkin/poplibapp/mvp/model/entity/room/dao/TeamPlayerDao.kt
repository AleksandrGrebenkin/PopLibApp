package com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.dao

import androidx.room.*
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.RoomTeamPlayer

@Dao
interface TeamPlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teamPlayer: RoomTeamPlayer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg teamPlayers: RoomTeamPlayer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teamPlayers: List<RoomTeamPlayer>)

    @Update
    fun update(teamPlayer: RoomTeamPlayer)

    @Update
    fun update(vararg teamPlayers: RoomTeamPlayer)

    @Update
    fun update(teamPlayers: List<RoomTeamPlayer>)

    @Delete
    fun delete(teamPlayer: RoomTeamPlayer)

    @Delete
    fun delete(vararg teamPlayers: RoomTeamPlayer)

    @Delete
    fun delete(teamPlayers: List<RoomTeamPlayer>)

    @Query("SELECT * FROM RoomTeamPlayer")
    fun getAll(): List<RoomTeamPlayer>

    @Query("SELECT * FROM RoomTeamPlayer WHERE teamId = :teamId")
    fun findForTeam(teamId: Int): List<RoomTeamPlayer>
}