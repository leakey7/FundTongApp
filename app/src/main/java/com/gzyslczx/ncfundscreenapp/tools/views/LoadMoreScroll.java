package com.gzyslczx.ncfundscreenapp.tools.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

public class LoadMoreScroll extends NestedScrollView {

    private OnLoadMoreScrollListener scrollViewListener;

    public LoadMoreScroll(@NonNull Context context) {
        super(context);
    }

    public LoadMoreScroll(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreScroll(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = (View) getChildAt(getChildCount() - 1);
        int diff = (view.getBottom() - (getHeight() + getScrollY()));
        if (diff == 0) {
            if (scrollViewListener != null) {
                scrollViewListener.onScrollEnded(this, l, t, oldl, oldt);
            }

        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void setScrollViewListener(OnLoadMoreScrollListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }
}
