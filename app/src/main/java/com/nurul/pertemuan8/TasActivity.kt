package com.nurul.pertemuan8

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurul.pertemuan8.Database.AppRoomDB
import com.nurul.pertemuan8.Database.Constant
import com.nurul.pertemuan8.Database.Tas
import kotlinx.android.synthetic.main.activity_tas.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TasActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var helmAdapter: TasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tas)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadTas()
    }

    fun loadTas(){
        CoroutineScope(Dispatchers.IO).launch {
            val allTas = db.tasDao().getAllHelm()
            Log.d("HelmActivity", "dbResponse: $allTas")
            withContext(Dispatchers.Main) {
                tasAdapter.setData(allTas)
            }
        }
    }

    fun setupListener() {
        btn_createHelm.setOnClickListener {
           intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        tasAdapter = TasAdapter(arrayListOf(), object: TasAdapter.OnAdapterListener {
            override fun onClick(tas: Tas) {
                intentEdit(tas.id, Constant.TYPE_READ)
            }

            override fun onDelete(tas: Tas) {
                deleteDialog(tas)
            }

        })
        list_tas.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = helmAdapter
        }
    }

    fun intentEdit(tasId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditTasActivity::class.java)
                .putExtra("intent_id", tasId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun deleteDialog(tas: Tas) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus data ini?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.tasDao().deleteTas(tas)
                    loadTas()
                }
            }
        }
        alertDialog.show()
    }
}