package com.architecture.domain

import com.architecture.domain.model.Main

interface MainRepository {

    suspend fun main(): Main
}