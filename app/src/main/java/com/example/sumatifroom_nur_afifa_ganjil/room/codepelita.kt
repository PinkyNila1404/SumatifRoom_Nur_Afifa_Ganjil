package com.example.sumatifroom_nur_afifa_ganjil.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [tb_karyawan::class],
    version =1
)
abstract class codepelita : RoomDatabase(){

    abstract fun  KaryawanDAO() : karyawanDAO

    companion object{

        @Volatile private  var instance : codepelita? = null
        private val  LOCK = Any()

        operator fun invoke (context: Context) =
        instance?: kotlin.synchronized(LOCK) {
        instance?: buildDatabase(context).also {
        instance = it
        }

    }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
           context.applicationContext,
           codepelita::class.java,
           "nis12345.db"
        ).build()
    }}