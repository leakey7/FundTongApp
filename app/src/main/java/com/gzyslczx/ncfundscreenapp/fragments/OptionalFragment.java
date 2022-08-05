package com.gzyslczx.ncfundscreenapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gzyslczx.ncfundscreenapp.databinding.OptionalFragmentBinding;

public class OptionalFragment extends BaseFragment<OptionalFragmentBinding> {
    @Override
    public void InitView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = OptionalFragmentBinding.inflate(inflater, container, false);
    }
}
