package com.example.sumatifroom_nur_afifa_ganjil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_nur_afifa_ganjil.room.tb_karyawan
import kotlinx.android.synthetic.main.activity_karyawan_adapter.view.*

class karyawanAdapter (private val Pekerja: ArrayList<tb_karyawan>,private val listener:
OnAdapterListener):
        RecyclerView.Adapter<karyawanAdapter.PekerjaViewHolder>(){

    class PekerjaViewHolder (var view: View) :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PekerjaViewHolder {
        return PekerjaViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_karyawan_adapter,parent,false)
        )
    }

    override fun onBindViewHolder(holder: PekerjaViewHolder, position: Int) {
        val pkr = Pekerja[position]
        holder.view.T_Nama.text = pkr.NAMA
        holder.view.T_ID.text = pkr.ID.toString()
        holder.view.T_Nama.setOnClickListener{
            listener.onClik(pkr)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(pkr)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(pkr)
        }
    }

    override fun getItemCount() = Pekerja.size

    fun setData (list: List<tb_karyawan>){
        Pekerja.clear()
        Pekerja.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener{
        fun onClik(tbKaryawan: tb_karyawan)
        fun onUpdate(tbKaryawan: tb_karyawan)
        fun onDelete(tbKaryawan: tb_karyawan)
    }}
