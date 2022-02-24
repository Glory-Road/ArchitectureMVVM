package com.architecture.mvvm.app.earn

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import com.architecture.base.BaseUiActivity
import com.architecture.base.utils.SystemUiUtils
import com.architecture.mvvm.databinding.ActivitySubscribeBinding

class SubscribeActivity : BaseUiActivity<ActivitySubscribeBinding>() {

    override fun getViewBinding(inflater: LayoutInflater) = ActivitySubscribeBinding.inflate(inflater)

    override fun setupViews() {

    }

    override fun setupListeners() {
        mBinding.toolbar.setNavigationOnClickListener {
            finish()
        }

    }

    override fun setupObservers() {

    }

    override fun onResume() {
        super.onResume()
        SystemUiUtils.makeStatusBarThemeLight(window, false)

    }
    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context, SubscribeActivity::class.java))
        }
    }
}