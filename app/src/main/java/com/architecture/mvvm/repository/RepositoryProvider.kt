package com.architecture.mvvm.repository

import android.content.Context
import com.architecture.data.MainRepository
import com.architecture.data.SignRepository
import com.architecture.data.Store
import com.architecture.mvvm.AppConfig

/**
 *     author : wnq
 *     time   : 2022/01/17
 *     desc   :
 */
object RepositoryProvider {
    private lateinit var store: Store

    fun inject(context: Context) {
        store = Store(context, AppConfig.BASE_URL)
    }

    fun mainRepository(): MainRepository = MainRepository(store)

    fun signRepository(): SignRepository = SignRepository(store)
}