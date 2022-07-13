package com.gzyslczx.ncfundscreenapp;

import android.app.Application;

import com.gzyslczx.ncfundscreenapp.tools.SpTool;

public class MyApp extends Application {

    private final String TAG = "MyApp";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化SpTool
        SpTool.Instance(this);
    }
}
