package com.architecture.base

import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    fun request(block: ()->Unit, error: ()->Unit) {

    }
}