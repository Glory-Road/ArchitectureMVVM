package com.architecture.mvvm.app.assets

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import com.architecture.base.BaseUiActivity
import com.architecture.mvvm.databinding.ActivityReceiveDetailBinding

class ReceiveDetailActivity : BaseUiActivity<ActivityReceiveDetailBinding>() {
    override fun getViewBinding(inflater: LayoutInflater) = ActivityReceiveDetailBinding.inflate(inflater)

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
            context.startActivity(Intent(context, ReceiveDetailActivity::class.java))
        }
    }
}