package com.architecture.mvvm.app.assets

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import com.architecture.base.BaseUiActivity
import com.architecture.mvvm.databinding.ActivityReceiveListBinding

class ReceiveListActivity: BaseUiActivity<ActivityReceiveListBinding>() {

    override fun getViewBinding(inflater: LayoutInflater) = ActivityReceiveListBinding.inflate(inflater)

    override fun setupViews() {


    }

    override fun setupListeners() {
        mBinding.toolbar.setNavigationOnClickListener {
            finish()
        }

        mBinding.productItem1.root.setOnClickListener {
            ReceiveDetailActivity.start(this)
        }
    }

    override fun setupObservers() {
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context, ReceiveListActivity::class.java))
        }
    }
}