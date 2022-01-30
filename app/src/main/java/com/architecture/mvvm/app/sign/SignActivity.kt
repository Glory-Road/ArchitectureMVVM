package com.architecture.mvvm.app.sign

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.architecture.base.BaseActivity
import com.architecture.mvvm.R

class SignActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
    }

    companion object{

        fun start(context: Context) {
            val intent = Intent(context, SignActivity::class.java)
            context.startActivity(intent)
        }
    }
}