package com.architecture.mvvm.app.sign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.architecture.base.BaseFragment
import com.architecture.base.extensions.viewBinding
import com.architecture.mvvm.databinding.SignFragmentBinding

class SignFragment: BaseFragment() {
    private val binding: SignFragmentBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
    override fun setupViews() {

    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
    }
}