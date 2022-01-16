package com.architecture.data

import com.architecture.data.mapper.toDomain
import com.architecture.data.net.apiService
import com.architecture.domain.MainRepository
import com.architecture.domain.model.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainDataRepository: MainRepository{
    override suspend fun main(): Flow<Main> {
        return flow {
            apiService.getMain(1).toDomain()
        }
    }


}