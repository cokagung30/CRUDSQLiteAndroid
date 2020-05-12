package com.baliwork.trainingcrudsqlite.interfaces

import com.baliwork.trainingcrudsqlite.sqlite.table.Biodata

interface BiodataClickListener {
    fun onClick(position: Int, biodatas: List<Biodata>, action: String)
}