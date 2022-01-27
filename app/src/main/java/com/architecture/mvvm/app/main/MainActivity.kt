package com.architecture.mvvm.app.main

import android.os.Bundle
import com.architecture.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(android.R.id.content, MainFragment()).commit()
    }
}