package com.architecture.mvvm.app.sign

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import com.architecture.base.BaseFragment
import com.architecture.base.extensions.viewBinding
import com.architecture.mvvm.R
import com.architecture.mvvm.databinding.SignFragmentBinding
import com.architecture.mvvm.widget.LoadingDialog
import com.blankj.utilcode.util.RegexUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SignFragment: BaseFragment() {
    private val binding: SignFragmentBinding by viewBinding()
    private val viewModel: SignViewModel by viewModels()

    private val mLoadingDialog by lazy { LoadingDialog(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun showLoading() {
        mLoadingDialog.setMessage("sign in")
        mLoadingDialog.show()
    }

    override fun dismissLoading() {
        mLoadingDialog.dismiss()
    }

    override fun setupViews() {
    }

    override fun setupListeners() {
        binding.titleSignIn.setOnClickListener {
            lifecycleScope.launch {
                viewModel.signPage.emit(PAGE_SIGN_IN)
            }
        }

        binding.titleSignUp.setOnClickListener {
            lifecycleScope.launch {
                viewModel.signPage.emit(PAGE_SIGN_UP)
            }
        }
    }

    override fun setupObservers() {
        lifecycleScope.launch {
            whenCreated {
                viewModel.signPage.collectLatest { page ->
                    changeUiPage(page)
                }
            }
        }
    }

    private fun changeUiPage(page: Int) {
        binding.titleSignIn.isSelected = PAGE_SIGN_IN == page
        binding.titleSignUp.isSelected = PAGE_SIGN_UP == page
        binding.titleSignIn.paint.isFakeBoldText = PAGE_SIGN_IN == page
        binding.titleSignUp.paint.isFakeBoldText = PAGE_SIGN_UP == page

        when(page) {
            PAGE_SIGN_IN -> {
                childFragmentManager.commit {
                    replace(R.id.fragment_container, SignInFragment())
                }
            }
            PAGE_SIGN_UP -> {
                childFragmentManager.commit {
                    replace(R.id.fragment_container, SignUpFragment())
                }
            }
        }
    }

    companion object{
        const val PAGE_SIGN_IN = 1
        const val PAGE_SIGN_UP = 2
    }
}