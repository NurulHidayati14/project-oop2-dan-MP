package com.nurul.pertemuan8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.nurul.pertemuan8.Database.AppRoomDB
import com.nurul.pertemuan8.Database.Constant
import com.nurul.pertemuan8.Database.Tas
import kotlinx.android.synthetic.main.activity_edit_tas.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class EditTasActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var tasId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_tas)
        setupListener()
        setupView()
    }

    fun setupListener(){
        btn_saveTas.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.tasDao().addTas(
                    Tas(
                        0,
                        txt_type.text.toString(),
                        Integer.parseInt(txt_merk.text.toString()),
                        Integer.parseInt(txt_stok.text.toString()),
                        Integer.parseInt(txt_harga.text.toString())
                    )
                )
                finish()
            }
        }
    }

fun setupView() {
    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    val intentType = intent.getIntExtra("intent_type", 0)
    when (intentType) {
        Constant.TYPE_CREATE -> {

        }
        Constant.TYPE_READ -> {
            btn_saveTas.visibility = View.GONE
            getTas()
        }
    }
}
    fun getTas() {
        tasId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val tasm =  db.tasDao().getTas( tasId )[0]
            txt_type.setText( tasm.type )
            txt_merk.setText( tasm.merk )
            txt_stok.setText( tasm.stok.toString() )
            txt_harga.setText( tasm.harga.toString() )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}