package com.architecture.data

import com.architecture.domain.SignRepository

class SignDataRepository(private val store: Store): SignRepository {
    override suspend fun login() {

    }

    override suspend fun register() {
    }

    override suspend fun verifyCode() {
    }
}