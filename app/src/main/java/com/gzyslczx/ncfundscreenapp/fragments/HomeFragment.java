package com.gzyslczx.ncfundscreenapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gzyslczx.ncfundscreenapp.databinding.HomeFragmentBinding;

public class HomeFragment extends BaseFragment<HomeFragmentBinding>{


    @Override
    public View InitView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = HomeFragmentBinding.inflate(inflater, container, false);
        return mViewBinding.getRoot();
    }
}
