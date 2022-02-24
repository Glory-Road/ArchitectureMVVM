package com.architecture.mvvm.app.sign

import androidx.lifecycle.ViewModel
import com.architecture.base.ComponentState
import com.architecture.domain.model.Message
import com.architecture.mvvm.app.sign.SignFragment.Companion.PAGE_SIGN_IN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class SignViewModel: ViewModel() {

    val signPage = MutableStateFlow(PAGE_SIGN_IN)
    val _loginResult by lazy { MutableStateFlow()<ComponentState<Message>>() }

    fun login(email: String, password: String) {
        return flow {
            kotlinx.coroutines.delay(500)
        }.onStart {

        }
    }

    fun register(email: String, password: String) {

    }

    fun verifyCode() {

    }
}