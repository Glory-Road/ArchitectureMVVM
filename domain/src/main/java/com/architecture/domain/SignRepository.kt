package com.architecture.domain

import com.architecture.domain.model.HttpResult
import com.architecture.domain.model.Message
import java.util.concurrent.Flow

interface SignRepository {

    suspend fun login(email: String, password: String): Flow<HttpResult>

    suspend fun register()

    suspend fun verifyCode()
}