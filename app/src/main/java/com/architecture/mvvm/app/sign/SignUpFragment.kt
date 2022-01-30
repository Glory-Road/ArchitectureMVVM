package com.architecture.mvvm.app.sign

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import com.architecture.base.BaseFragment
import com.architecture.base.extensions.viewBinding
import com.architecture.mvvm.databinding.SignInFragmentBinding
import com.architecture.mvvm.databinding.SignUpFragmentBinding
import com.blankj.utilcode.util.RegexUtils

class SignUpFragment: BaseFragment() {
    private val binding: SignUpFragmentBinding by viewBinding()

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
        binding.email.setOnFocusChangeListener { inputView, hasFocus ->
            if (hasFocus.not()) {
                inputView as EditText
                val isEmail = RegexUtils.isEmail(inputView.text)
                binding.emailErrorTips.isVisible = isEmail.not()
                Toast.makeText(requireContext(), "邮箱格式${if (isEmail) "正确" else "错误"}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setupObservers() {
        binding.root.postDelayed( {
            requestShowKeyboard()
        }, 300)
    }

    private fun requestShowKeyboard() {
        binding.root.requestFocus()
        showKeyboard(binding.email)
    }

    private fun showKeyboard(focusView: EditText) {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(focusView, 0)
    }
}