package com.baliwork.trainingcrudsqlite.sqlite.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baliwork.trainingcrudsqlite.sqlite.table.Biodata

@Dao
interface BiodataDao {

    //Untuk menjalankan query  menyimpan data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBiodata(biodata: Biodata)

    //Untuk memperbaharui Biodata User
    @Query("Update Biodatas Set nama=:namaBaru Where idUser=:id")
    fun updatBiodata(namaBaru: String, id: Int)

    //Untuk mengambil data yang berada pada Tabel Biodata
    @Query("Select * from Biodatas")
    fun fetchBiodata() : LiveData<List<Biodata>>

}