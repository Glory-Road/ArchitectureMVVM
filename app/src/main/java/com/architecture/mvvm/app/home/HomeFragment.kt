package com.architecture.mvvm.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.architecture.base.BaseFragment
import com.architecture.base.extensions.viewBinding
import com.architecture.mvvm.app.earn.EarnActivity
import com.architecture.mvvm.databinding.HomeFragmentBinding
import com.architecture.mvvm.databinding.SwapFragmentBinding

/**
 *     author : wnq
 *     time   : 2022/01/27
 *     desc   :
 */
class HomeFragment: BaseFragment() {

    private val binding: HomeFragmentBinding by viewBinding()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun setupViews() {
    }

    override fun setupListeners() {
        binding.productItem1.root.setOnClickListener {
            EarnActivity.start(requireContext())
        }
    }

    override fun setupObservers() {
    }
}