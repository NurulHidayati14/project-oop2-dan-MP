package com.nurul.pertemuan8

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JurusanDao {
    @Query("SELECT * FROM jurusan")
    fun getAll(): List<Jurusan>

    @Query("SELECT * FROM jurusan WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Jurusan>

    @Query("SELECT * FROM jurusan WHERE nama_jurusan LIKE :nama_jurusan LIMIT 1")
    fun findByName(nama_jurusan: String): Jurusan

    @Insert
    fun insertAll(vararg jurusan: Jurusan)

    @Delete
    fun delete(jurusan: Jurusan)
}
