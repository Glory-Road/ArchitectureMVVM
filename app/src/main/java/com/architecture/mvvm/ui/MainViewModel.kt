package com.architecture.mvvm.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.architecture.base.BaseViewModel
import com.architecture.base.ComponentState
import com.architecture.domain.model.ArticleDetail
import com.architecture.mvvm.repository.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 *     author : wnq
 *     time   : 2022/01/26
 *     desc   :
 */
class MainViewModel : BaseViewModel() {
    private val repository = RepositoryProvider.mainRepository()
    val livedata = MutableLiveData<ComponentState<List<ArticleDetail>>>(ComponentState.Loading())


    fun main() = viewModelScope.launch {
        repository.main()
            .catch {
                Log.e("WNQ", it.message, it)
                livedata.value = ComponentState.Error(11, "222")
            }
            .collectLatest {
                if (it.errorCode == 200) {
                    livedata.value = ComponentState.Success(it.data)
                } else {
                    livedata.value = ComponentState.Empty()
                }
            }
    }
}