package com.gzyslczx.ncfundscreenapp.tools.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class MyHorScroll extends HorizontalScrollView {

    private HorizontalScrollView subScroll;

    public MyHorScroll(Context context) {
        super(context);
    }

    public MyHorScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSubScroll(HorizontalScrollView subScroll) {
        this.subScroll = subScroll;
    }

    public MyHorScroll(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (subScroll != null) {
            subScroll.scrollTo(l, t);
        }
    }

}
