  
package com.nurul.pertemuan8

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "siswa")
data class Siswa(
    @PrimaryKey val nis: Int,
    @ColumnInfo(name = "nama") val nama: String?,
    @ColumnInfo(name = "kelas") val kelas: String?
)
