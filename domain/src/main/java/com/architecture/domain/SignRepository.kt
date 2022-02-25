package com.architecture.domain

import com.architecture.domain.model.Message

interface SignRepository {

    suspend fun login(email: String, password: String)

    suspend fun register()

    suspend fun verifyCode()
}