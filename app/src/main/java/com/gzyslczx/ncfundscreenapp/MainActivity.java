package com.gzyslczx.ncfundscreenapp;

import androidx.core.app.ActivityCompat;

import com.gzyslczx.ncfundscreenapp.databinding.ActivityMainBinding;
import com.gzyslczx.ncfundscreenapp.events.UpdateTokenEvent;
import com.gzyslczx.ncfundscreenapp.presenter.MainActPresenter;
import com.gzyslczx.ncfundscreenapp.tools.TokenTool;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private final String TAG = "MainAct";

    private MainActPresenter mPresenter;

    @Override
    public void InitLayout() {
        mViewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());
        getWindow().setStatusBarColor(ActivityCompat.getColor(this, R.color.main_blue));
        EventBus.getDefault().register(this);
    }

    @Override
    public void InitViews() {
        mPresenter = new MainActPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TokenTool.Instance().CheckTokenEffective()){
            mPresenter.RequestUpdateToken(TAG, 0, this, null);
        }else {
            //无需更新Token
            Init();
        }
    }

    /*
    * Token更新事件
    * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnUpdateTokenEvent(UpdateTokenEvent event){
        if (event.isSuccess()){
            //Token更新成功
            Init();
        }
    }

    /*
    * 初始化功能模块
    * */
    private void Init(){

    }



}