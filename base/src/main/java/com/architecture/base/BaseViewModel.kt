package com.architecture.base

import androidx.lifecycle.ViewModel

class BaseViewModel: ViewModel() {

    fun request(block: ()->Unit, error: ()->Unit) {

    }
}