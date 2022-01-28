package com.architecture.mvvm.app.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.architecture.base.BaseFragment
import com.architecture.base.extensions.viewBinding
import com.architecture.mvvm.databinding.MeFragmentBinding

/**
 *     author : wnq
 *     time   : 2022/01/27
 *     desc   :
 */
class MeFragment: BaseFragment() {

    private val binding: MeFragmentBinding by viewBinding()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun setupViews() {
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
    }
}