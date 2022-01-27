package com.architecture.base.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.IdRes
import com.architecture.base.R

/**
 * 显示页面状态的View
 *
 *
 * Created by Administrator on 2016/8/29 0029.
 */
class StatusLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    var loadingview: View? = null
    var errorView: View? = null
    var emptyView: View? = null
    var emptyRecommendView: View? = null
    private var mContent: View? = null
    private var mDefault: State

    private val cachedViews = mutableMapOf<Int, View>()

    override fun onFinishInflate() {
        super.onFinishInflate()
        mContent = getChildAt(0)
        setStatus(mDefault, true)
    }

    fun getState() = mDefault

    fun loading(){
        setStatus(State.LOADING, false)
    }

    fun error(){
        setStatus(State.ERROR, false)
    }

    fun empty(){
        setStatus(State.EMPTY, false)
    }

    fun content(){
        setStatus(State.CONTENT, false)
    }

    fun emptyRecommend(){
        setStatus(State.EMPTY_RECOMMEND, false)
    }
    fun <T: View> getView(state: State, @IdRes resId: Int): T {
        val view = when(state){
            State.LOADING -> loadingview
            State.EMPTY -> emptyView
            State.ERROR -> errorView
            State.CONTENT -> mContent
            State.EMPTY_RECOMMEND -> emptyRecommendView
        }?: throw IllegalArgumentException("No such state.")

        return cachedViews[resId] as T? ?: view.findViewById<T>(resId).apply {
            cachedViews[resId] = this
        }
    }

    private fun setStatus(status: State, force: Boolean) {
        if (!force && status == mDefault) {
            return
        }
        mDefault = status
        when (status) {
            State.LOADING -> showView(loadingview)
            State.EMPTY -> showView(emptyView)
            State.EMPTY_RECOMMEND -> showView(emptyRecommendView)
            State.ERROR -> showView(errorView)
            State.CONTENT -> showView(mContent)
        }
    }

    private fun showView(view: View?) {
        val childAt: View? = getChildAt(1)
        if (childAt != null) {
            removeView(childAt)
        }
        if (view === mContent) {
            mContent?.apply {
                visibility = View.VISIBLE
                alpha = 0f
                animate().alpha(1f)
            }
        } else {
            mContent?.visibility = View.INVISIBLE
            addView(view).apply {
                alpha = 0f
                animate().alpha(1f).duration = 200
            }
        }
    }

    init {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.StatusLayout)
        val loadingRes = a.getResourceId( R.styleable.StatusLayout_loadingLayout, 0)
        val errorRes = a.getResourceId( R.styleable.StatusLayout_errorLayout, 0)
        val emptyRes = a.getResourceId( R.styleable.StatusLayout_emptyLayout, 0)
        val emptyRecommendRes = a.getResourceId( R.styleable.StatusLayout_emptyRecommendLayout, 0)
        val inflater: LayoutInflater = LayoutInflater.from(context)
        if (loadingRes != 0) {
            loadingview = inflater.inflate(loadingRes, null)
        }
        if (errorRes != 0) {
            errorView = inflater.inflate(errorRes, null)
        }
        if (emptyRes != 0) {
            emptyView = inflater.inflate(emptyRes, null)
        }
        if (emptyRecommendRes != 0) {
            emptyRecommendView = inflater.inflate(emptyRecommendRes, null)
        }
        mDefault = State.fromIndex(a.getInt(R.styleable.StatusLayout_defaultShow, 0))
        a.recycle()
    }

    enum class State(private val index: Int) {
        LOADING(0), EMPTY(1), ERROR(2),CONTENT(3),EMPTY_RECOMMEND(4);

        companion object {
            fun fromIndex(index: Int): State {
                return values()[index]
            }
        }
    }
}