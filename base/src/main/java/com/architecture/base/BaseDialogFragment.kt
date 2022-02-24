package com.architecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

/**
 *     author : wnq
 *     time   : 2022/01/17
 *     desc   :
 */
abstract class BaseDialogFragment<VB: ViewBinding>: DialogFragment() {

    val binding: VB
        get() = _binding!!
    private var _binding: VB? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
        setupObservers()
    }

    protected abstract fun setupViews()
    protected abstract fun setupListeners()
    protected abstract fun setupObservers()

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    protected fun checkLogin() {

    }
}