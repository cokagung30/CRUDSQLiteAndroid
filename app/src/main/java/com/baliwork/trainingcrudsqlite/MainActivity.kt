package com.baliwork.trainingcrudsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.baliwork.trainingcrudsqlite.sqlite.AppDatabase
import com.baliwork.trainingcrudsqlite.sqlite.table.Biodata
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_submit_biodata.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_submit_biodata -> {
                val nama = edt_nama.text.toString()
                val umur = edt_umur.text.toString()
                val noTelp = edt_no_telp.text.toString()
                val alamat = edt_alamat.text.toString()

                val biodata = Biodata(
                    0, nama, umur.toInt(), noTelp, alamat
                )

                AppDatabase.getInstance().biodataDao().saveBiodata(biodata)
            }
        }
    }
}
