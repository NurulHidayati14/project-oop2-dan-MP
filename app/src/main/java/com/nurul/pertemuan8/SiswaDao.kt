package com.nurul.pertemuan8

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SiswaDao {
    @Query("SELECT * FROM siswa")
    fun getAll(): List<Siswa>

    @Query("SELECT * FROM siswa WHERE nim IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Siswa>

    @Query("SELECT * FROM siswa WHERE nama LIKE :nama AND " +
            "kelas LIKE :kelas LIMIT 1")
    fun findByName(nama: String, semester: String): Siswa

    @Insert
    fun insertAll(vararg siswa: Siswa)

    @Delete
    fun delete(siswa: Siswa)
}