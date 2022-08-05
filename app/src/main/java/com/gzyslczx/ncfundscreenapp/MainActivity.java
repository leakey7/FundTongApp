package com.gzyslczx.ncfundscreenapp;

import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.app.ActivityCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.google.android.material.navigation.NavigationBarView;
import com.gzyslczx.ncfundscreenapp.databinding.ActivityMainBinding;
import com.gzyslczx.ncfundscreenapp.databinding.LoadingLayoutBinding;
import com.gzyslczx.ncfundscreenapp.events.UpdateTokenEvent;
import com.gzyslczx.ncfundscreenapp.fragments.BaseFragment;
import com.gzyslczx.ncfundscreenapp.fragments.FindFragment;
import com.gzyslczx.ncfundscreenapp.fragments.HomeFragment;
import com.gzyslczx.ncfundscreenapp.fragments.MineFragment;
import com.gzyslczx.ncfundscreenapp.fragments.OptionalFragment;
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
    private PopupWindow mLoadingView;
    List<BaseFragment> fragments;


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
        //初始底部导航栏
        mViewBinding.MainBottom.setItemIconTintList(null);
        mViewBinding.MainBottom.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);
        //初始切页
        mFragmentAdapter = new FragmentAdapter(this);
        mFragmentAdapter.setFragments(InitFragments());
        //初始加载中试图
        LoadingLayoutBinding loadingLayoutBinding = LoadingLayoutBinding.bind(getLayoutInflater().inflate(R.layout.loading_layout, null));
        mLoadingView = new PopupWindow(loadingLayoutBinding.getRoot(), 200, 200);
        //底部导航栏点击事件
        mViewBinding.MainBottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.MainBottomHome:
                        mViewBinding.MainPagers.setCurrentItem(0, false);
                        break;
                    case R.id.MainBottomFind:
                        mViewBinding.MainPagers.setCurrentItem(1, false);
                        break;
                    case R.id.MainBottomSelect:
                        mViewBinding.MainPagers.setCurrentItem(2, false);
                        break;
                    case R.id.MainBottomMine:
                        mViewBinding.MainPagers.setCurrentItem(3, false);
                        break;
                }
                return false;
            }
        });
        mLoadingView.setOutsideTouchable(false);
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
            if (mPresenter==null){
                mPresenter = new MainActPresenter();
            }
            Log.d(TAG, "更新Token");
            mPresenter.RequestUpdateToken(TAG, 0, this, null);
        }else {
            //无需更新Token
            Init();
        }
    }

    /*
    * 初始化功能模块
    * */
    private void Init(){
        mViewBinding.MainPagers.setAdapter(mFragmentAdapter);
        mViewBinding.MainPagers.setUserInputEnabled(false);
        mViewBinding.MainPagers.setOffscreenPageLimit(4);
        mViewBinding.MainPagers.setCurrentItem(0, false);
    }

    /*
    * 初始化Fragment集
    * */
    private List<BaseFragment> InitFragments(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new FindFragment());
        fragments.add(new OptionalFragment());
        fragments.add(new MineFragment());
        return fragments;
    }

    /*
    * 显示加载动画
    * */
    private void ShowLoading(){
        if (mLoadingView!=null){
            mLoadingView.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }
    }

    /*
    * 销毁加载动画
    * */
    private void DismissLoading(){
        if (mLoadingView!=null){
            mLoadingView.dismiss();
        }
    }


    /*
     * Token更新事件
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnUpdateTokenEvent(UpdateTokenEvent event){
        if (event.isSuccess()){
            //Token更新成功
            Log.d(TAG, "更新Token成功");
            Init();
        }else {
            Log.d(TAG, "更新Token失败");
            Toast.makeText(this, "无法获取权限", Toast.LENGTH_SHORT).show();
        }
    }

}