package com.baliwork.trainingcrudsqlite.sqlite.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Biodatas")
class Biodata (
    @PrimaryKey(autoGenerate = true)
    val idUser: Int,
    val nama: String,
    val umur: Int?,
    val noTelp: String?,
    val alamat: String?
)