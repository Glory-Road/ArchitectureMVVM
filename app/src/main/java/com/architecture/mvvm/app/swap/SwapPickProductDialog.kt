package com.architecture.mvvm.app.swap

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.architecture.base.BaseDialogFragment
import com.architecture.mvvm.databinding.DialogSwapPickProductBinding

class SwapPickProductDialog: BaseDialogFragment<DialogSwapPickProductBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogSwapPickProductBinding = DialogSwapPickProductBinding.inflate(inflater, container, false)

    override fun setupViews() {

    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setGravity(Gravity.BOTTOM)
    }
}