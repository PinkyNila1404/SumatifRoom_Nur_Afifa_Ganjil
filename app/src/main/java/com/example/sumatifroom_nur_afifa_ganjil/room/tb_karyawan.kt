package com.example.sumatifroom_nur_afifa_ganjil.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tb_karyawan (
    @PrimaryKey(autoGenerate = true)
    val ID : Int,
    val NAMA : String,
    val ALAMAT : String,
    val USIA : Int)