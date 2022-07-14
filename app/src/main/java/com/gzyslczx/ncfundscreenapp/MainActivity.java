package com.gzyslczx.ncfundscreenapp;

import com.gzyslczx.ncfundscreenapp.databinding.ActivityMainBinding;
import com.gzyslczx.ncfundscreenapp.tools.TokenTool;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private final String TAG = "MainAct";

    @Override
    public void InitLayout() {
        mViewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());
    }

    @Override
    public void InitViews() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TokenTool.Instance().CheckTokenEffective()){
            
        }
    }
}