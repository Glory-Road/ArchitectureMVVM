package com.architecture.mvvm.app.sign

import androidx.lifecycle.ViewModel
import com.architecture.mvvm.app.sign.SignFragment.Companion.PAGE_SIGN_IN
import kotlinx.coroutines.flow.MutableStateFlow

class SignViewModel: ViewModel() {

    val signPage = MutableStateFlow(PAGE_SIGN_IN)

    fun login(email: String, password: String) {

    }

    fun register(email: String, password: String) {

    }

    fun verifyCode() {

    }
}