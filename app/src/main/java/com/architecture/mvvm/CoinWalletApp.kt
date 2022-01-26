package com.architecture.mvvm

import android.app.Application
import com.architecture.mvvm.repository.RepositoryProvider

/**
 *     author : wnq
 *     time   : 2022/01/17
 *     desc   :
 */
class CoinWalletApp: Application() {

    override fun onCreate() {
        super.onCreate()
        RepositoryProvider.inject(this)
    }
}