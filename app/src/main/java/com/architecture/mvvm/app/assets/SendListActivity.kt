package com.architecture.mvvm.app.assets

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.architecture.base.BaseUiActivity
import com.architecture.mvvm.R
import com.architecture.mvvm.databinding.ActivitySendListBinding

class SendListActivity : BaseUiActivity<ActivitySendListBinding>() {
    override fun getViewBinding(inflater: LayoutInflater) = ActivitySendListBinding.inflate(inflater)

    override fun setupViews() {
    }

    override fun setupListeners() {
        mBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
        mBinding.productItem1.root.setOnClickListener {
            SendDetailActivity.start(this)
        }
    }

    override fun setupObservers() {
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context, SendListActivity::class.java))
        }
    }
}