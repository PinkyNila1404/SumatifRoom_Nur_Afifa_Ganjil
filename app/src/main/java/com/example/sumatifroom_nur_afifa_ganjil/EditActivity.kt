package com.example.sumatifroom_nur_afifa_ganjil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sumatifroom_nur_afifa_ganjil.room.Constant
import com.example.sumatifroom_nur_afifa_ganjil.room.codepelita
import com.example.sumatifroom_nur_afifa_ganjil.room.tb_karyawan
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    val db by lazy { codepelita(this) }
    private var tbpekerjaID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tbpekerjaID = intent.getIntExtra("intent_id", 0)
        Toast.makeText(this, tbpekerjaID.toString(), Toast.LENGTH_SHORT).show()
        simpandata()
        setupView()

    }

    fun setupView() {
        val intentType = intent.getIntExtra("intent_Type", 0)
        when (intentType) {
            Constant.Type_Cerate -> {
                btn_update.visibility = View.GONE
            }
            Constant.Type_READ -> {
                btn_save.visibility = View.GONE
                btn_update.visibility = View.GONE
            }
            Constant.Type_Update -> {
                btn_save.visibility = View.GONE
                ET_ID.visibility = View.GONE
                Tampilpekerja()
            }
        }
    }

    fun Tampilpekerja() {
        tbpekerjaID = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val pekerja = db.KaryawanDAO().tampilsemua(tbpekerjaID)[0]
            val usia : String = pekerja.USIA.toString()
            ET_Nama.setText(pekerja.NAMA)
            ET_Alamat.setText(pekerja.ALAMAT)
            ET_Usia.setText(usia)
        }
    }

    fun simpandata() {
        btn_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.KaryawanDAO().addkaryawan(
                    tb_karyawan(
                        ET_ID.text.toString().toInt(),
                        ET_Nama.text.toString(),
                        ET_Alamat.text.toString(),
                        ET_Usia.text.toString().toInt()
                    )
                )
                finish()
            }
        }
        btn_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.KaryawanDAO().updatekaryawan(
                    tb_karyawan(
                        tbpekerjaID,
                        ET_Nama.text.toString(),
                        ET_Alamat.text.toString(),
                        ET_Usia.text.toString().toInt()
                    )
                )
                finish()
            }
        }
    }

}
