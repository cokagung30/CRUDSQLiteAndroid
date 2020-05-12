package com.baliwork.trainingcrudsqlite

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.baliwork.trainingcrudsqlite.adapter.BiodataAdapter
import com.baliwork.trainingcrudsqlite.interfaces.BiodataClickListener
import com.baliwork.trainingcrudsqlite.sqlite.AppDatabase
import com.baliwork.trainingcrudsqlite.sqlite.table.Biodata
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, BiodataClickListener {

    companion object {
        const val ACTION_UPDATE = "update"
        const val ACTION_DELETE = "delete"
    }

    private lateinit var biodataAdapter: BiodataAdapter
    private var idUser: Int? = null
    private var status: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_submit_biodata.setOnClickListener(this)

        showBiodata()
    }

    private fun showBiodata() {
        rv_biodata.setHasFixedSize(true)
        AppDatabase.getInstance().biodataDao().fetchBiodata()
            .observe(this, Observer { biodatas ->
                biodataAdapter = BiodataAdapter(biodatas, this)
                rv_biodata.adapter = biodataAdapter
            })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_submit_biodata -> {
                val nama = edt_nama.text.toString()
                val umur = edt_umur.text.toString()
                val noTelp = edt_no_telp.text.toString()
                val alamat = edt_alamat.text.toString()

                val biodata = Biodata(
                    0, nama, umur.toInt(), noTelp, alamat
                )
                if (status) {
                    AppDatabase.getInstance().biodataDao().saveBiodata(biodata)
                } else {
                    status = true
                    AppDatabase.getInstance().biodataDao().updatBiodata(nama, idUser)
                }
            }
        }
    }

    override fun onClick(position: Int, biodatas: List<Biodata>, action: String) {
        if (action == ACTION_UPDATE) {
            setEditTextData(biodatas[position])
        } else if (action == ACTION_DELETE) {
            showAlertDelete(biodatas[position].idUser)
        }
    }

    private fun showAlertDelete(idUser: Int) {
        AlertDialog.Builder(this)
            .setTitle("Apakah anda ingin menghapus data ini ?")
            .setPositiveButton("Hapus"
            ) { dialog, which -> AppDatabase.getInstance().biodataDao().deleteBiodata(idUser) }
            .setNegativeButton("No"
            ) { dialog, which -> dialog?.dismiss() }.show()
    }

    private fun setEditTextData(biodata: Biodata) {
        edt_nama.setText(biodata.nama)
        edt_umur.setText(biodata.umur.toString())
        edt_no_telp.setText(biodata.noTelp)
        edt_alamat.setText(biodata.alamat)

        status = false
        idUser = biodata.idUser
        btn_submit_biodata.setOnClickListener(this)
    }
}
