package com.architecture.mvvm.app.main

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import com.architecture.base.BaseActivity
import com.architecture.base.utils.SystemUiUtils
import com.architecture.base.utils.SystemUtils

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUtils.hideSystemUiVisibility(window.decorView)
        //适配刘海屏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
//        SystemUiUtils.makeTransparentStatusBar(window)
//        SystemUiUtils.makeStatusBarThemeLight(window, true)
        supportFragmentManager.beginTransaction().replace(android.R.id.content, MainFragment()).commit()
    }
}