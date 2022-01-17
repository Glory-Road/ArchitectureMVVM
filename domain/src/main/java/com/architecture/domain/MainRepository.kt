package com.architecture.domain

import com.architecture.domain.model.Main
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun main(): Flow<Main>
}