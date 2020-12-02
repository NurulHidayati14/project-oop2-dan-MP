package com.nurul.pertemuan8

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = arrayOf(Siswa::class, Jurusan::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun siswaDao(): SiswaDao
    abstract fun jurusanDao(): JurusanDao

    abstract val applicationContext: Context
    val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "APPDB"
    ).build()
}
