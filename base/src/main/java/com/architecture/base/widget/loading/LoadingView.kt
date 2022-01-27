package com.architecture.base.widget.loading

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.toColorInt

class LoadingView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    private var mLoadingDrawable: LoadingDrawable = LoadingDrawable(ColorStateList.valueOf("#0E7B70".toColorInt()), 10)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    init {
        background = mLoadingDrawable
        mLoadingDrawable.start()
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == VISIBLE){
            if (mLoadingDrawable.isRunning.not()){
                mLoadingDrawable.start()
            }
        } else {
            if (mLoadingDrawable.isRunning){
                mLoadingDrawable.stop()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

}