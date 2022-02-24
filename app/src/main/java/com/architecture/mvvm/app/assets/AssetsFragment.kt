package com.architecture.mvvm.app.assets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.architecture.base.BaseFragment
import com.architecture.base.extensions.viewBinding
import com.architecture.mvvm.databinding.AssetsFragmentBinding

/**
 *     author : wnq
 *     time   : 2022/01/27
 *     desc   :
 */
class AssetsFragment: BaseFragment() {

    private val binding: AssetsFragmentBinding by viewBinding()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun setupViews() {
    }

    override fun setupListeners() {
        binding.btnReceive.setOnClickListener {
            ReceiveListActivity.start(requireContext())
        }
        binding.btnSend.setOnClickListener {
            SendListActivity.start(requireContext())
        }
    }

    override fun setupObservers() {
    }
}