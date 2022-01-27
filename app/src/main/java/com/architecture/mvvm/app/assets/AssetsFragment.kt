package com.architecture.mvvm.app.assets

import android.view.LayoutInflater
import android.view.ViewGroup
import com.architecture.base.BaseFragment
import com.architecture.mvvm.databinding.AssetsFragmentBinding
import com.architecture.mvvm.databinding.HomeFragmentBinding

/**
 *     author : wnq
 *     time   : 2022/01/27
 *     desc   :
 */
class AssetsFragment: BaseFragment<AssetsFragmentBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): AssetsFragmentBinding {
        return AssetsFragmentBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
    }
}