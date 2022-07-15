package com.gzyslczx.ncfundscreenapp.fragments;

import androidx.viewbinding.ViewBinding;

import com.trello.rxlifecycle4.components.RxFragment;

public abstract class BaseFragment<T extends ViewBinding> extends RxFragment {

    public T mViewBinding;



}
