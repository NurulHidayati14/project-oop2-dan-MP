package com.nurul.pertemuan8.Database

import androidx.room.*

@Dao
interface TasDao {
    @Insert
    suspend fun addTas(tas: Tas)

    @Update
    suspend fun updateTas(tas: Tas)

    @Delete
    suspend fun deleteTas(tas: Tas)

    @Query("SELECT * FROM tas")
    suspend fun getAllTas(): List<Tas>

    @Query("SELECT * FROM tas WHERE id=:tas_id")
    suspend fun getTas(tas_id: Int) : List<Tas>

}