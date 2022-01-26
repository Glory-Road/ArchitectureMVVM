package com.architecture.mvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.architecture.base.BaseActivity
import com.architecture.mvvm.R

class MainActivity : BaseActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.livedata.observe(this){
            Log.w("WNQ", "onCreate: $it")
        }
        viewModel.main()
    }
}