package com.gzyslczx.ncfundscreenapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

public abstract class BaseActivity<T extends ViewBinding> extends RxAppCompatActivity {

    public T mViewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitLayout();
        InitViews();
    }

    /*
    * 初始化布局
    * */
    public abstract void InitLayout();

    /*
    * 初始化控件
    * */
    public abstract void InitViews();

}
