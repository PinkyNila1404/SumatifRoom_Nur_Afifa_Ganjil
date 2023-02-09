package com.example.sumatifroom_nur_afifa_ganjil.room

import androidx.room.*

@Dao
interface karyawanDAO {

    @Insert
   fun addkaryawan (tbKaryawan: tb_karyawan)

    @Update
    fun updatekaryawan (tbKaryawan: tb_karyawan)

    @Delete
    fun delkaryawan (tbKaryawan: tb_karyawan)

    @Query("SELECT * FROM tb_karyawan")
    fun tampilsemuaa () :List<tb_karyawan>

    @Query("SELECT * FROM tb_karyawan WHERE ID =:pekerja_ID ")
    fun tampilsemua (pekerja_ID: Int) :List<tb_karyawan>
}