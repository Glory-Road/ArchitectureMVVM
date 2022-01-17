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
        _binding = getViewBinding()
        return binding.root
    }

    abstract fun getViewBinding(): VB

    protected fun checkLogin() {

    }
}