package com.architecture.mvvm.app.assets

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import com.architecture.base.BaseUiActivity
import com.architecture.mvvm.databinding.ActivitySendDetailBinding

class SendDetailActivity : BaseUiActivity<ActivitySendDetailBinding>() {

    override fun getViewBinding(inflater: LayoutInflater) = ActivitySendDetailBinding.inflate(inflater)

    override fun setupViews() {
    }

    override fun setupListeners() {
        mBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun setupObservers() {
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context, SendDetailActivity::class.java))
        }
    }
}