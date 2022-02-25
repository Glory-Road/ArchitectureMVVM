package com.architecture.mvvm.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import com.architecture.mvvm.R

/**
 * 加载进度框
 *
 *
 * Created by Administrator on 2016/8/30 0030.
 */
class LoadingDialog(context: Context) : Dialog(context, R.style.Theme_AppCompat_Dialog_Loading_Fullscreen) {
    private lateinit var mTextView: TextView
    private var mMessage: CharSequence? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        mTextView = findViewById(R.id.dialog_loading_text)
        window?.setGravity(Gravity.CENTER)
    }

    fun setMessage(message: CharSequence) {
        mMessage = message
    }

    override fun show() {
        super.show()
        mMessage?.let {
            mTextView.text = it
        }
    }

    init {
        setCancelable(true)
        setCanceledOnTouchOutside(false)
    }
}