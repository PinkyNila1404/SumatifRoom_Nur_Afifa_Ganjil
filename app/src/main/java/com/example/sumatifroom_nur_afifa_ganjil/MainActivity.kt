package com.example.sumatifroom_nur_afifa_ganjil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_nur_afifa_ganjil.room.Constant
import com.example.sumatifroom_nur_afifa_ganjil.room.codepelita
import com.example.sumatifroom_nur_afifa_ganjil.room.tb_karyawan
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    val db by lazy { codepelita(this) }
    lateinit var KaryawanAdapter :karyawanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setupRecylerView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }
        fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val perkerja = db.KaryawanDAO().tampilsemuaa()
            Log.d("MainActivity", "dbResponse: $perkerja")
            withContext(Dispatchers.Main) {
                KaryawanAdapter.setData(perkerja)
            }
        }
    }

    fun halEdit() {
        btn_input.setOnClickListener {
         intentEdit(0,Constant.Type_Cerate)
        }}

    fun intentEdit(idpekerja: Int ,intentType : Int) {
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id", idpekerja)
                .putExtra("intent_Type", intentType)
        )

    }
    private fun setupRecylerView() {
        KaryawanAdapter = karyawanAdapter(arrayListOf(), object :
            karyawanAdapter.OnAdapterListener {
            override fun onClik(tbKaryawan: tb_karyawan) {
                intentEdit(tbKaryawan.ID, Constant.Type_READ)
            }

            override fun onUpdate(tbKaryawan: tb_karyawan) {
                intentEdit(tbKaryawan.ID, Constant.Type_Update)
            }

            override fun onDelete(tbKaryawan: tb_karyawan) {
                deleteDialog(tbKaryawan)
            }
        }
        )
        list_data.apply {
           layoutManager = LinearLayoutManager(applicationContext)
            adapter = KaryawanAdapter
        }
    }
    private fun  deleteDialog(tbKaryawan: tb_karyawan) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("komfirmasi")
            setMessage("Yakin hapus ${tbKaryawan.NAMA}?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.KaryawanDAO().delkaryawan(tbKaryawan)
                    loadData()
                }
            }
        }
        alertDialog.show()
    }
}