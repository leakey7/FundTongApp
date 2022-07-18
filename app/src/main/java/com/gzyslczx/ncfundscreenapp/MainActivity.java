package com.gzyslczx.ncfundscreenapp;

import androidx.core.app.ActivityCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.google.android.material.navigation.NavigationBarView;
import com.gzyslczx.ncfundscreenapp.databinding.ActivityMainBinding;
import com.gzyslczx.ncfundscreenapp.events.UpdateTokenEvent;
import com.gzyslczx.ncfundscreenapp.fragments.BaseFragment;
import com.gzyslczx.ncfundscreenapp.fragments.HomeFragment;
import com.gzyslczx.ncfundscreenapp.presenter.MainActPresenter;
import com.gzyslczx.ncfundscreenapp.tools.FragmentAdapter;
import com.gzyslczx.ncfundscreenapp.tools.TokenTool;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private final String TAG = "MainAct";

    private MainActPresenter mPresenter;
    private WindowInsetsControllerCompat mWICCompact;
    private FragmentAdapter mFragmentAdapter;

    @Override
    public void InitLayout() {
        mViewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());
        getWindow().setStatusBarColor(ActivityCompat.getColor(this, R.color.main_blue));
        mWICCompact = WindowCompat.getInsetsController(getWindow(), mViewBinding.getRoot());
        mWICCompact.setAppearanceLightStatusBars(false);
        EventBus.getDefault().register(this);
    }

    @Override
    public void InitViews() {
        mPresenter = new MainActPresenter();
        mViewBinding.MainBottom.setItemIconTintList(null);
        mViewBinding.MainBottom.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);
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
        if (mFragmentAdapter==null){
            mFragmentAdapter = new FragmentAdapter(this);
            mFragmentAdapter.setFragments(InitFragments());
            mViewBinding.MainPagers.setAdapter(mFragmentAdapter);
        }
    }

    /*
    * 初始化Fragment集
    * */
    private List<BaseFragment> InitFragments(){
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        return fragments;
    }


}