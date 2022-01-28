package com.architecture.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
        setupObservers()
    }

    protected abstract fun setupViews()
    protected abstract fun setupListeners()
    protected abstract fun setupObservers()

    protected fun checkLogin() {

    }
}