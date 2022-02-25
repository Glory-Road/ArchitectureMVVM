package com.architecture.mvvm.app.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.architecture.base.BaseViewModel
import com.architecture.base.ComponentState
import com.architecture.mvvm.R
import com.architecture.mvvm.repository.RepositoryProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 *     author : wnq
 *     time   : 2022/01/26
 *     desc   :
 */
class MainViewModel : BaseViewModel() {
    private val repository = RepositoryProvider.mainRepository()
//    val livedata = MutableLiveData<ComponentState<List<ArticleDetail>>>(ComponentState.Loading())
    val navigationPosition = MutableStateFlow(R.id.main_bottom_nav_home)

    fun main() = viewModelScope.launch {
//        repository.main()
//            .onStart {
//                livedata.value = ComponentState.Loading()
//            }
//            .catch {
//                Log.e("WNQ", it.message, it)
//                livedata.value = ComponentState.Error(11, "222")
//            }
//            .onCompletion {
//
//            }
//            .collectLatest {
//                if (it.errorCode == 0) {
//                    if (it.data.isEmpty()) {
//                        livedata.value = ComponentState.Empty()
//                    } else {
//                        delay(500)
//                        livedata.value = ComponentState.Success(it.data)
//                    }
//                } else {
//                    livedata.value = ComponentState.Error(11, "222")
//                }
//            }
    }
}