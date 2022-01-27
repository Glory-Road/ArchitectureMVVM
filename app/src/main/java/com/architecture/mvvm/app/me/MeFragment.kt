package com.architecture.mvvm.app.me

import android.view.LayoutInflater
import android.view.ViewGroup
import com.architecture.base.BaseFragment
import com.architecture.mvvm.databinding.HomeFragmentBinding
import com.architecture.mvvm.databinding.MeFragmentBinding

/**
 *     author : wnq
 *     time   : 2022/01/27
 *     desc   :
 */
class MeFragment: BaseFragment<MeFragmentBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): MeFragmentBinding {
        return MeFragmentBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
    }
}