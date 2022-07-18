package com.gzyslczx.ncfundscreenapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.trello.rxlifecycle4.components.support.RxFragment;

public abstract class BaseFragment<T extends ViewBinding> extends RxFragment {

    public T mViewBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return InitView(inflater, container, savedInstanceState);
    }

    public abstract View InitView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);



}
