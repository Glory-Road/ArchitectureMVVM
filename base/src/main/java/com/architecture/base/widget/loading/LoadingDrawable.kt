package com.architecture.base.widget.loading

import android.content.res.ColorStateList
import android.graphics.*
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.SystemClock
import kotlin.math.*

/**
 * 加载动画
 * 调用start()开始动画，stop()结束动画
 */
class LoadingDrawable(colorStateList: ColorStateList, pointSize: Int) : Drawable(), Animatable, Runnable {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mColorStateList = colorStateList

    private var mColor = colorStateList.defaultColor

    private val mBounds = Rect()

    private var mOffset = 0f
    private var mPointCount = pointSize
    private var mMaxRadius = 1f
    private var mMinRadius = 1f

    private var mRunning = false
    private var mLastTime = 0L
    private val DELAY = 15

    init {
        mPaint.style = Paint.Style.FILL
    }

    override fun isRunning(): Boolean  = mRunning

    override fun start() {
        if (isRunning){
            return
        }

        mRunning = true
        mLastTime = SystemClock.uptimeMillis()
        scheduleSelf(this, 0)
        invalidateSelf()
    }

    override fun stop() {
        if (!isRunning) return

        mRunning = false
        unscheduleSelf(this)
        invalidateSelf()
    }

    override fun run() {
        val now = SystemClock.uptimeMillis()
        val deltaTime = now - mLastTime
        mLastTime = now
        mOffset = (mOffset + mMaxRadius / 60 * deltaTime / DELAY) % mMaxRadius
        scheduleSelf(this, now + DELAY)
        invalidateSelf()
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        val size = min(bounds.width(), bounds.height())
        mBounds.set(bounds.left, bounds.top, bounds.left + size, bounds.top + size)
        computeRadius()
        invalidateSelf()
    }

    private fun computeRadius(){
        val width = mBounds.width() / 2
        val m = sqrt(2 - 2 * cos(2 * PI / mPointCount))
        val len = m * width / (2 + m) * 0.7
        mMaxRadius = len.toFloat()
        mMinRadius = (len / 3).toFloat()
    }

    override fun onStateChange(state: IntArray?): Boolean {
        val color = mColorStateList.getColorForState(state, mColor)
        if (color != mColor) {
            mColor = color
            invalidateSelf()
            return true
        }
        return false
    }

    override fun isStateful(): Boolean {
        return true
    }

    override fun draw(canvas: Canvas) {
        mPaint.color = mColor

        val delta = 360f / mPointCount
        val deltaE = mMaxRadius / mPointCount
        canvas.save()
        canvas.translate(bounds.width().toFloat() / 2, 0f)
        for (i in 0 until mPointCount) {
            canvas.rotate(delta, 0f, (bounds.height() / 2).toFloat())
            canvas.drawCircle(0f, mMaxRadius, mMaxRadius - abs(mMaxRadius + mOffset - deltaE * i) % mMaxRadius, mPaint)
        }
        canvas.restore()
    }

    override fun setAlpha(p0: Int) {
        mPaint.alpha = p0
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(p0: ColorFilter?) {
        mPaint.colorFilter = p0
    }
}