package com.architecture.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.architecture.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}