package com.architecture.mvvm.app.swap

import android.view.LayoutInflater
import android.view.ViewGroup
import com.architecture.base.BaseFragment
import com.architecture.mvvm.databinding.HomeFragmentBinding
import com.architecture.mvvm.databinding.SwapFragmentBinding

/**
 *     author : wnq
 *     time   : 2022/01/27
 *     desc   :
 */
class SwapFragment: BaseFragment<SwapFragmentBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): SwapFragmentBinding {
        return SwapFragmentBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
    }
}