/*
 * MIT License
 *
 * Copyright (c) 2016 Srijith Narayanan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.architecture.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.architecture.base.utils.SystemUiUtils;

public class HeaderFitStatusBarBehavior<V extends ViewGroup> extends CoordinatorLayout.Behavior<V> {
    private int mStatusBarHeight;
    public HeaderFitStatusBarBehavior() { }
    public HeaderFitStatusBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            int[] themeAttrs = new int[]{android.R.attr.windowTranslucentStatus};
            TypedArray typedArray = context.obtainStyledAttributes(themeAttrs);
            boolean windowTranslucentStatus = typedArray.getBoolean(0, false);
            if (windowTranslucentStatus) {
                mStatusBarHeight = SystemUiUtils.getStatusBarHeight();
            }
            typedArray.recycle();
        }
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, V child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        if (mStatusBarHeight == 0){
            return false;
        }
        int height = View.MeasureSpec.getSize(parentHeightMeasureSpec);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.UNSPECIFIED);
        child.measure(parentWidthMeasureSpec, heightSpec);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(child.getMeasuredHeight() + mStatusBarHeight, View.MeasureSpec.EXACTLY);
        child.measure(parentWidthMeasureSpec, heightMeasureSpec);
        return true;
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull V child, int layoutDirection) {
        if (child instanceof LinearLayout) {
            ((LinearLayout)child).setGravity(Gravity.BOTTOM);
        }
        return super.onLayoutChild(parent, child, layoutDirection);
    }
}
