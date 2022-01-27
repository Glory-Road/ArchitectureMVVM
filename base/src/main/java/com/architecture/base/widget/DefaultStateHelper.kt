package com.architecture.base.widget

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.architecture.base.R

class DefaultStateHelper(private var view: StatusLayout? = null): LifecycleEventObserver {

    private var lifecycleOwner: LifecycleOwner ?= null

    fun registerLifecycle(lifecycleOwner: LifecycleOwner): DefaultStateHelper{
        this.lifecycleOwner = lifecycleOwner
        lifecycleOwner.lifecycle.addObserver(this)
        return this
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY){
            lifecycleOwner?.lifecycle?.removeObserver(this)
            view = null
        }
    }

    /**
     * 設置加載狀態文案
     */
    fun setupLoading(desc: String): DefaultStateHelper {
        view?.apply {
            getView<TextView>(StatusLayout.State.LOADING, R.id.state_loading_desc)
                .text = desc
        }
        return this
    }

    /**
     * 設置空狀態圖片以及文案
     */
    fun setupEmpty(@DrawableRes image: Int, desc: String): DefaultStateHelper {
        view?.apply {
            getView<TextView>(StatusLayout.State.EMPTY, R.id.state_empty_desc)
                .text = desc

            getView<ImageView>(StatusLayout.State.EMPTY, R.id.state_empty_image)
                .setImageResource(image)
        }
        return this
    }


    /**
     * 設置空狀態圖片以及文案和背景颜色
     */
    fun setupEmpty(@DrawableRes image: Int, desc: String, color: Int, textColor: Int? = null): DefaultStateHelper {
        view?.apply {
            val emptyDescView = getView<TextView>(StatusLayout.State.EMPTY, R.id.state_empty_desc)
            emptyDescView.text = desc
            textColor?.let {
                emptyDescView.setTextColor(textColor)
            }

            getView<ImageView>(StatusLayout.State.EMPTY, R.id.state_empty_image)
                .setImageResource(image)

            getView<View>(StatusLayout.State.EMPTY, R.id.state_empty_view)
                .setBackgroundColor(color)
        }
        return this
    }

    /**
     * 設置錯誤狀態文案、重試事件, 使用了一个默认的错误图片
     */
    fun setupError(desc: String, retryListener: View.OnClickListener): DefaultStateHelper {
        return setupError(R.drawable.ic_launcher_background, desc, retryListener)
    }

    /**
     * 設置錯誤狀態圖片、文案、重試事件
     */
    fun setupError(@DrawableRes image: Int, desc: String, retryListener: View.OnClickListener): DefaultStateHelper {
        view?.apply {
            getView<TextView>(StatusLayout.State.ERROR, R.id.state_error_desc)
                .text = desc

            getView<ImageView>(StatusLayout.State.ERROR, R.id.state_error_image)
                .setImageResource(image)

            val retry = getView<TextView>(StatusLayout.State.ERROR, R.id.state_error_retry)
            retry.setOnClickListener(retryListener)
            retry.paint.isUnderlineText = true
        }
        return this
    }

    fun updateLoadingText(desc: String) {
        view?.apply {
            getView<TextView>(StatusLayout.State.LOADING, R.id.state_loading_desc)
                .text = desc
        }
    }

    fun updateErrorText(desc: String) {
        view?.apply {
            getView<TextView>(StatusLayout.State.ERROR, R.id.state_error_desc)
                .text = desc
        }
    }

    fun loading() {
        view?.loading()
    }

    fun empty() {
        view?.empty()
    }

    fun content() {
        view?.content()
    }

    fun error() {
        view?.error()
    }

    fun isSuccess() = view?.getState() == StatusLayout.State.CONTENT

    fun isError() = view?.getState() == StatusLayout.State.ERROR
}