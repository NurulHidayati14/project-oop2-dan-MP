package com.nurul.pertemuan8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nurul.pertemuan8.Database.AppRoomDB
import com.nurul.pertemuan8.Database.Constant
import com.nurul.pertemuan8.Database.User
import kotlinx.android.synthetic.main.activity_edit_tas.*
import kotlinx.android.synthetic.main.activity_edit_user.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditUserActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        setupListener()
        setupView()
    }

    fun setupListener(){
        btn_saveUser.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().addUser(
                    User(0, txt_nama.text.toString(), txt_username.text.toString())
                )
                finish()
            }
        }
    }

    fun setupView() {
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {

            }
            Constant.TYPE_READ -> {
                btn_saveUser.visibility = View.GONE
                getUser()
            }
        }
    }

    fun getUser() {
        userId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val users =  db.userDao().getUser( userId )[0]
            txt_nama.setText( users.nama )
            txt_username.setText( users.username )
        }
    }
}