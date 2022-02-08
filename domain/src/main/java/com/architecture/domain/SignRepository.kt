package com.architecture.domain

import com.architecture.domain.model.Message
import java.util.concurrent.Flow

interface SignRepository {

    suspend fun login()

    suspend fun register()

    suspend fun verifyCode()
}