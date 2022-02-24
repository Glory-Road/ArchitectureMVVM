package com.architecture.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class BaseUiActivity<VB: ViewBinding> : BaseActivity() {

    private var _binding: VB? = null
    protected val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding(layoutInflater)
        setContentView(mBinding.root)
        setupViews()
        setupListeners()
        setupObservers()
    }


    protected abstract fun getViewBinding(inflater: LayoutInflater): VB
    protected abstract fun setupViews()
    protected abstract fun setupListeners()
    protected abstract fun setupObservers()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}