package com.architecture.mvvm.app.swap

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.architecture.base.BaseDialogFragment
import com.architecture.mvvm.databinding.DialogSwapConfirmBinding
import com.blankj.utilcode.util.ToastUtils

class SwapConfirmDialog: BaseDialogFragment<DialogSwapConfirmBinding>() {
    override fun setupViews() {

    }

    override fun setupListeners() {
        binding.btnCancel.setOnClickListener { dismiss() }
        binding.btnSend.setOnClickListener {
            dismiss()
            Toast.makeText(requireContext(), "Your swap is completed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setupObservers() {
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DialogSwapConfirmBinding.inflate(inflater, container, false)

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout((resources.displayMetrics.widthPixels*0.89f).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}