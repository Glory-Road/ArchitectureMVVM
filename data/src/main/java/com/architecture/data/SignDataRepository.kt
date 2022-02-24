package com.architecture.data

import com.architecture.domain.SignRepository

class SignDataRepository(private val store: Store): SignRepository {
    override suspend fun login(email: String, password: String) {

    }

    override suspend fun register() {
    }

    override suspend fun verifyCode() {
    }
}