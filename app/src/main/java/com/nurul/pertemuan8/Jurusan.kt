package com.nurul.pertemuan8

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jurusan")
data class Jurusan(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "nama_jurusan") val nama_jurusan: String?
)
