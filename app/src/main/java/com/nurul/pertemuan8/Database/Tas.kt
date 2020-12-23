package com.nurul.pertemuan8.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "helm")
data class Tas(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "merk") val merk: String,
    @ColumnInfo(name = "stok") val stok: Int,
    @ColumnInfo(name = "harga") val harga: Int
)