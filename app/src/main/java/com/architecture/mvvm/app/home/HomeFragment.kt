package com.architecture.mvvm.app.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.architecture.base.BaseFragment
import com.architecture.mvvm.databinding.HomeFragmentBinding

/**
 *     author : wnq
 *     time   : 2022/01/27
 *     desc   :
 */
class HomeFragment: BaseFragment<HomeFragmentBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): HomeFragmentBinding {
        return HomeFragmentBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
    }
}