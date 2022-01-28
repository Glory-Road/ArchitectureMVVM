package com.architecture.base.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.architecture.base.LifecycleAwareViewBinding

/**
 *     author : wnq
 *     time   : 2022/01/28
 *     desc   :
 */
inline fun <reified V : ViewBinding> Fragment.viewBinding(): LifecycleAwareViewBinding<Fragment, V> {
    val method = V::class.java.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    return LifecycleAwareViewBinding { method.invoke(null, layoutInflater, null, false) as V }
}