package com.gzyslczx.ncfundscreenapp.fragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gzyslczx.ncfundscreenapp.R;
import com.gzyslczx.ncfundscreenapp.databinding.HomeFragmentBinding;
import com.gzyslczx.ncfundscreenapp.events.AdvEvent;
import com.gzyslczx.ncfundscreenapp.presenter.HomeFragPres;
import com.gzyslczx.ncfundscreenapp.tools.FragmentAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeFragment extends BaseFragment<HomeFragmentBinding> implements View.OnClickListener {

    private final String TAG = "HomeFrag";
    private HomeFragPres mPresenter;
    private FragmentAdapter mChatAdapter;

    @Override
    public View InitView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = HomeFragmentBinding.inflate(inflater, container, false);
        //沪深三百对比图切页
        mChatAdapter = new FragmentAdapter(this);
        //基金筛选点击
        mViewBinding.HomeFragSelector.setOnClickListener(this::onClick);
        EventBus.getDefault().register(this);
        return mViewBinding.getRoot();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter==null){
            mPresenter = new HomeFragPres();
            Init();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    /*
    * 初始化功能
    * */
    private void Init(){
        //请求基金筛选
        mPresenter.RequestFundSelectPic(TAG, this);
        //
    }

    /*
    * 基金筛选图
    * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnFundSelectEvent(AdvEvent advEvent){
        if (advEvent.getType()==1){
            if (advEvent.isSuccess()){
                Glide.with(getContext()).load(advEvent.getAdvObjList().get(0).getImg())
                        .fitCenter()
                        .error(ContextCompat.getDrawable(getContext(), R.drawable.load_error))
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                mViewBinding.HomeFragSelector.setTag("ERROR");
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                mViewBinding.HomeFragSelector.setTag(advEvent.getAdvObjList().get(0).getAppUrl());
                                return false;
                            }
                        })
                        .into(mViewBinding.HomeFragSelector);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.HomeFragSelector:
                if (mViewBinding.HomeFragSelector.getTag()!=null) {
                    if ("error".equals(mViewBinding.HomeFragSelector.getTag().toString())) {
                        mViewBinding.HomeFragSelector.setImageDrawable(null);
                        mPresenter.RequestFundSelectPic(TAG, this);
                        Log.d(TAG, "跳转基金筛选无点击地址");
                    } else {
                        Log.d(TAG, String.format("跳转基金筛选：%s", mViewBinding.HomeFragSelector.getTag().toString()));
                    }
                }else {
                    Log.d(TAG, "跳转基金筛选无点击地址");
                }
                break;
        }
    }
}
