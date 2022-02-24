package com.architecture.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import java.util.List;

/**
 * make a view below the view it anchored
 * <p>
 * Created by Administrator on 2017/7/10 0010.
 */

public class BelowAnchorBehavior extends CoordinatorLayout.Behavior<View> {
    private float dy;
    private float dty;
    public BelowAnchorBehavior() {
        super();
    }

    public BelowAnchorBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        dy = dependency.getBottom();
//        child.offsetTopAndBottom((int) dy);
//        dty = dependency.getTranslationY();
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        child.setY(dy + layoutParams.topMargin);
        return true;
    }


    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        final int childLpHeight = child.getLayoutParams().height;
        if (childLpHeight == ViewGroup.LayoutParams.MATCH_PARENT
                || childLpHeight == ViewGroup.LayoutParams.WRAP_CONTENT) {
            List<View> dependencies = parent.getDependencies(child);

            int headerHeight = 0;
            for (int i = 0; i < dependencies.size(); i++){
                headerHeight += dependencies.get(i).getMeasuredHeight();
            }

            if (headerHeight > 0) {
                int availableHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec) - headerHeight;
                int spec = View.MeasureSpec.makeMeasureSpec(availableHeight, childLpHeight == ViewGroup.LayoutParams.MATCH_PARENT
                        ? View.MeasureSpec.EXACTLY
                        : View.MeasureSpec.AT_MOST);
                parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, spec, heightUsed);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
        return true;
    }
}
