package com.architecture.mvvm.app.earn

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.architecture.base.BaseUiActivity
import com.architecture.mvvm.databinding.ActivityEarnBinding

class EarnActivity: BaseUiActivity<ActivityEarnBinding>() {

    override fun getViewBinding(inflater: LayoutInflater) = ActivityEarnBinding.inflate(inflater)

    override fun setupViews() {
    }

    override fun setupListeners() {
        mBinding.toolbar.setNavigationOnClickListener {
            finish()
        }

        mBinding.btnSubscribe.setOnClickListener {
            SubscribeActivity.start(this)
        }
    }

    override fun setupObservers() {
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context, EarnActivity::class.java))
        }
    }
}