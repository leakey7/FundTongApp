package com.gzyslczx.ncfundscreenapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gzyslczx.ncfundscreenapp.databinding.MineFragmentBinding;

public class MineFragment extends BaseFragment<MineFragmentBinding> {
    @Override
    public void InitView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = MineFragmentBinding.inflate(inflater, container, false);
    }
}
