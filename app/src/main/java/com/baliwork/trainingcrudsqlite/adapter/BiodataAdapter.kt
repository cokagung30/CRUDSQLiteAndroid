package com.baliwork.trainingcrudsqlite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baliwork.trainingcrudsqlite.MainActivity
import com.baliwork.trainingcrudsqlite.R
import com.baliwork.trainingcrudsqlite.interfaces.BiodataClickListener
import com.baliwork.trainingcrudsqlite.sqlite.table.Biodata
import kotlinx.android.synthetic.main.item_biodata.view.*

class BiodataAdapter (val biodatas: List<Biodata>, val listener: BiodataClickListener) : RecyclerView.Adapter<BiodataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BiodataAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_biodata, parent, false))
    }

    override fun getItemCount(): Int = biodatas.size

    override fun onBindViewHolder(holder: BiodataAdapter.ViewHolder, position: Int) {
        holder.itemView.tv_nama.text = biodatas[position].nama
        holder.itemView.tv_umur.text = biodatas[position].umur.toString()
        holder.itemView.tv_alamat.text = biodatas[position].alamat

        holder.itemView.cv_biodata.setOnClickListener {
               listener.onClick(position, biodatas, MainActivity.ACTION_UPDATE)
        }

        holder.itemView.cv_biodata.setOnLongClickListener {
            listener.onClick(position, biodatas, MainActivity.ACTION_DELETE)
            return@setOnLongClickListener true
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}