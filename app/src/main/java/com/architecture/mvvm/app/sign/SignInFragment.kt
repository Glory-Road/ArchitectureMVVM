package com.architecture.mvvm.app.sign

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.architecture.base.BaseFragment
import com.architecture.base.extensions.viewBinding
import com.architecture.mvvm.databinding.SignInFragmentBinding
import com.architecture.mvvm.ext.launchWithLoadingAndCollect
import com.architecture.mvvm.widget.LoadingDialog
import com.blankj.utilcode.util.RegexUtils
import com.blankj.utilcode.util.ToastUtils

class SignInFragment: BaseFragment() {
    private val binding: SignInFragmentBinding by viewBinding()
    private val viewModel: SignViewModel by viewModels()
    private val mLoadingDialog by lazy { LoadingDialog(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        binding.signIn.setOnClickListener {
            login()
        }
    }

    override fun setupObservers() {
        binding.root.postDelayed( {
            requestShowKeyboard()
        }, 300)
    }

    override fun showLoading() {
        mLoadingDialog.setMessage("sign in")
        mLoadingDialog.show()
    }

    override fun dismissLoading() {
        mLoadingDialog.dismiss()
    }

    private fun login() {
        launchWithLoadingAndCollect({
            viewModel.login("FastJetpack", "FastJetpack")
        }) {
            onSuccess = {
                Toast.makeText(requireContext(), "登录成功", Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            }
            onFailed = { errorCode, errorMsg ->
                Toast.makeText(requireContext(), "登录失败", Toast.LENGTH_SHORT).show()
            }
        }
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