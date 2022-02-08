package com.architecture.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.architecture.base.utils.SystemUiUtils

open class BaseActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        SystemUiUtils.makeTransparentStatusBar(window)
        SystemUiUtils.makeStatusBarThemeLight(window, true)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        ev?.let {
            if (ev.action == MotionEvent.ACTION_DOWN) {
                // 获得当前得到焦点的View，
                val v = currentFocus
                if (isShouldHideInput(v, ev)) {
                    //根据判断关闭软键盘
                    val imm = getSystemService(
                            Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v?.windowToken, 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 判断用户点击的区域是否是输入框
     *
     * @param v
     * @param event
     * @return
     */
    private fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && (v is EditText)) {
            val l = intArrayOf(0, 0 )
            v.getLocationInWindow(l);
            val left = l[0]
            val top = l[1]
            val bottom = top + v.getHeight()
            val right = left+ v.getWidth();
            return !(event.x > left && event.x < right
                    && event.y > top && event.y < bottom)
        }
        return false;
    }

    fun checkLogin() {

    }
}