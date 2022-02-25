package com.architecture.mvvm.app.sign

import androidx.lifecycle.ViewModel
import com.architecture.data.model.ApiResponse
import com.architecture.data.model.ArticleDetail
import com.architecture.mvvm.app.sign.SignFragment.Companion.PAGE_SIGN_IN
import com.architecture.mvvm.repository.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow

class SignViewModel: ViewModel() {

    private val repository by lazy { RepositoryProvider.signRepository() }

    val signPage = MutableStateFlow(PAGE_SIGN_IN)
//    val _loginResult by lazy { MutableStateFlow()<ComponentState<Message>>() }

    suspend fun login(email: String, password: String): ApiResponse<List<ArticleDetail>> {
            return repository.login(email, password)
//        return flow {
//            kotlinx.coroutines.delay(500)
//        }.onStart {
//
//        }
    }

    fun register(email: String, password: String) {

    }

    fun verifyCode() {

    }
}