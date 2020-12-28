package com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.dao

import androidx.room.*
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.room.RoomTeam

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(team: RoomTeam)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg teams: RoomTeam)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teams: List<RoomTeam>)

    @Update
    fun update(team: RoomTeam)

    @Update
    fun update(vararg teams: RoomTeam)

    @Update
    fun update(teams: List<RoomTeam>)

    @Delete
    fun delete(team: RoomTeam)

    @Delete
    fun delete(vararg teams: RoomTeam)

    @Delete
    fun delete(teams: List<RoomTeam>)

    @Query("SELECT * FROM RoomTeam")
    fun getAll(): List<RoomTeam>

    @Query("SELECT * FROM RoomTeam WHERE name = :name LIMIT 1")
    fun findByName(name: String): RoomTeam?
}