package com.gzyslczx.ncfundscreenapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.gzyslczx.ncfundscreenapp.R;
import com.gzyslczx.ncfundscreenapp.WebActivity;
import com.gzyslczx.ncfundscreenapp.adapters.HomeRankLeftAdapter;
import com.gzyslczx.ncfundscreenapp.adapters.HomeRankRightAdapter;
import com.gzyslczx.ncfundscreenapp.beans.response.ResIconObj;
import com.gzyslczx.ncfundscreenapp.beans.response.ResIconType;
import com.gzyslczx.ncfundscreenapp.databinding.HomeFragmentBinding;
import com.gzyslczx.ncfundscreenapp.events.AdvEvent;
import com.gzyslczx.ncfundscreenapp.events.HomeRankEvent;
import com.gzyslczx.ncfundscreenapp.events.IconTabEvent;
import com.gzyslczx.ncfundscreenapp.presenter.HomeFragPres;
import com.gzyslczx.ncfundscreenapp.tools.FragmentAdapter;
import com.gzyslczx.ncfundscreenapp.tools.views.LoadMoreScroll;
import com.gzyslczx.ncfundscreenapp.tools.views.OnLoadMoreScrollListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class HomeFragment extends BaseFragment<HomeFragmentBinding> implements View.OnClickListener, OnLoadMoreListener {

    private final String TAG = "HomeFrag";
    private HomeFragPres mPresenter;
    private FragmentAdapter mChatAdapter;
    private TabLayoutMediator mTabLayoutMediator;
    private final String[] ChartTabs = new String[]{"1个月", "3个月", "6个月", "1年", "2年", "3年", "5年"};
    private List<ResIconObj> mIconTabList;
    private int mTabId, mTypeId, mNextPage = 1, mSort=1;
    private HomeRankLeftAdapter mRankLeftAdapter;
    private HomeRankRightAdapter mRankRightAdapter;

    @Override
    public View InitView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = HomeFragmentBinding.inflate(inflater, container, false);
        //沪深三百对比图切页
        mChatAdapter = new FragmentAdapter(this);
        //基金筛选点击
        mViewBinding.HomeFragSelector.setOnClickListener(this::onClick);
        //股票、混合、沪深三百
        mTabLayoutMediator = new TabLayoutMediator(mViewBinding.HomeFragChatTab, mViewBinding.HomeFragChartPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        });
        //Tab点击事件
        OnClickedIconTab();
        OnClickedSecondTab();
        //排行榜排序点击
        mViewBinding.HeadLeftSortImg.setOnClickListener(this::onClick);
        //排行榜列表适配
        mRankLeftAdapter = new HomeRankLeftAdapter(R.layout.home_rank_left_item);
        mViewBinding.HomeFragLeftList.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewBinding.HomeFragLeftList.setAdapter(mRankLeftAdapter);
        mRankRightAdapter = new HomeRankRightAdapter(R.layout.home_rank_right_item);
        mViewBinding.HomeFragRightList.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewBinding.HomeFragRightList.setAdapter(mRankRightAdapter);
        //排行榜列表右滑联动
        mViewBinding.HeadRightScroll.setSubScroll(mViewBinding.HomeFragScroll);
        mViewBinding.HomeFragScroll.setSubScroll(mViewBinding.HeadRightScroll);
        //滑动到底部监听
        mRankLeftAdapter.getLoadMoreModule().setOnLoadMoreListener(this::onLoadMore);
        mRankLeftAdapter.getLoadMoreModule().setAutoLoadMore(false);
        mRankRightAdapter.getLoadMoreModule().setAutoLoadMore(false);
        //注册EventBus
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
        if (mPresenter == null) {
            mPresenter = new HomeFragPres();
            Init();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //注销EventBus
        EventBus.getDefault().unregister(this);
    }

    /*
     * 初始化功能
     * */
    private void Init() {
        //请求基金筛选
        mPresenter.RequestFundSelectPic(TAG, this);
        //请求IconTab
        mPresenter.RequestTab(TAG, this);
    }

    /*
     * 基金筛选图
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnFundSelectEvent(AdvEvent advEvent) {
        if (advEvent.getType() == 1) {
            if (advEvent.isSuccess()) {
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

    /*
     * 基金IconTab
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnFundIconTabEvent(IconTabEvent event) {
        if (event.isSuccess()) {
            mIconTabList = event.getIconTabObj();
            InitIconTab();
        } else {
            mPresenter.RequestTab(TAG, this);
        }
    }

    /*
     * 基金排行榜
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnFundRankEvent(HomeRankEvent event) {
        if (event.isSuccess()) {
            //请求成功
            Log.d(TAG, String.format("当前页数：%d，总页数：%d",
                    event.getResMainRankObj().getCurrentPage(), event.getResMainRankObj().getPageCount()));
            if (mNextPage < event.getResMainRankObj().getPageCount()) {
                mNextPage++;
                //未到尾页
                mRankLeftAdapter.getLoadMoreModule().loadMoreComplete();
                mRankRightAdapter.getLoadMoreModule().loadMoreComplete();
            } else {
                //已到尾页
                mRankLeftAdapter.getLoadMoreModule().loadMoreEnd();
                mRankRightAdapter.getLoadMoreModule().loadMoreEnd();
            }
            //添加数据表
            mRankLeftAdapter.addData(event.getResMainRankObj().getPageInfo());
            mRankRightAdapter.addData(event.getResMainRankObj().getPageInfo());
        } else {
            //请求失败
            mRankLeftAdapter.getLoadMoreModule().loadMoreFail();
            mRankRightAdapter.getLoadMoreModule().loadMoreFail();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.HomeFragSelector:
                if (mViewBinding.HomeFragSelector.getTag() != null) {
                    if ("ERROR".equals(mViewBinding.HomeFragSelector.getTag().toString())) {
                        //基金筛选图加载失败，重请求
                        mPresenter.RequestFundSelectPic(TAG, HomeFragment.this);
                    } else {
                        //跳转基金筛选Web
                        Log.d(TAG, String.format("跳转基金筛选：%s", mViewBinding.HomeFragSelector.getTag().toString()));
                        Intent intent = new Intent(getContext(), WebActivity.class);
                        intent.putExtra(WebActivity.WebPath, mViewBinding.HomeFragSelector.getTag().toString());
                        startActivity(intent);
                    }
                } else {
                    Log.d(TAG, "无跳转基金筛选地址");
                }
                break;
            case R.id.HeadLeftSortImg:
                if (mViewBinding.HeadLeftSortImg.getTag().toString().equals("1")) {
                    //升序排序
                    mNextPage = 1;
                    mRankLeftAdapter.setList(null);
                    mRankRightAdapter.setList(null);
                    mSort = 0;
                    UpdateHomeRank();
                } else if (mViewBinding.HeadLeftSortImg.getTag().toString().equals("0")) {
                    //降序排序
                    mNextPage = 1;
                    mRankLeftAdapter.setList(null);
                    mRankRightAdapter.setList(null);
                    mSort = 1;
                    UpdateHomeRank();
                }

                break;
        }
    }

    /*
     * 初始化IconTab
     * */
    private void InitIconTab() {
        if (mIconTabList != null && mIconTabList.size() > 0) {
            Log.d(TAG, "初始化IconTab");
            mViewBinding.HomeFragTopTab.removeAllTabs();
            for (ResIconObj iconObj : mIconTabList) {
                TabLayout.Tab tab = mViewBinding.HomeFragTopTab.newTab();
                tab.setText(iconObj.getTitle());
                mViewBinding.HomeFragTopTab.addTab(tab);
            }
            mViewBinding.HomeFragTopTab.selectTab(mViewBinding.HomeFragTopTab.getTabAt(0));
        }
    }

    /*
     * 初始化二级Tab
     * */
    private void InitSecondTab(List<ResIconType> iconTypeList) {
        if (iconTypeList != null && iconTypeList.size() > 0) {
            mViewBinding.HomeFragSecondTab.removeAllTabs();
            for (ResIconType iconType : iconTypeList) {
                TabLayout.Tab tab = mViewBinding.HomeFragSecondTab.newTab();
                tab.setText(iconType.getTypeName());
                mViewBinding.HomeFragSecondTab.addTab(tab);
            }
            mViewBinding.HomeFragSecondTab.selectTab(mViewBinding.HomeFragSecondTab.getTabAt(0));
        }
    }

    /*
     * IconTab点击事件
     * */
    private void OnClickedIconTab() {
        mViewBinding.HomeFragTopTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //获取选择的IconTabID
                int select = mViewBinding.HomeFragTopTab.getSelectedTabPosition();
                mTabId = mIconTabList.get(select).getAdId();
                Log.d(TAG, String.format("一级Tab=%d", mTabId));
                //更新二级Tab
                InitSecondTab(mIconTabList.get(select).getTList());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /*
     * 二级Tab点击事件
     * */
    private void OnClickedSecondTab() {
        mViewBinding.HomeFragSecondTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //获取选择的二级TabID
                int select = mViewBinding.HomeFragSecondTab.getSelectedTabPosition();
                mTypeId = mIconTabList.get(mViewBinding.HomeFragTopTab.getSelectedTabPosition()).getTList().get(select).getTId();
                Log.d(TAG, String.format("二级Tab=%d", mTypeId));
                //更新排行榜
                mNextPage = 1;
                mRankLeftAdapter.setList(null);
                mRankRightAdapter.setList(null);
                mSort = 1;
                UpdateHomeRank();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /*
     * 更新排行榜
     * */
    private void UpdateHomeRank() {
        mPresenter.RequestHomeRank(TAG, this, mTabId, mTypeId, mNextPage, mSort);
        //更换排序图
        if (mSort == 1 && mViewBinding.HeadLeftSortImg.getTag().toString().equals("0")) {
            Glide.with(getContext()).load(ContextCompat.getDrawable(getContext(), R.drawable.da_xiao)).fitCenter()
                    .into(mViewBinding.HeadLeftSortImg);
            mViewBinding.HeadLeftSortImg.setTag("1");
        } else if (mSort == 0 && mViewBinding.HeadLeftSortImg.getTag().toString().equals("1")) {
            Glide.with(getContext()).load(ContextCompat.getDrawable(getContext(), R.drawable.xiao_da)).fitCenter()
                    .into(mViewBinding.HeadLeftSortImg);
            mViewBinding.HeadLeftSortImg.setTag("0");
        }
    }

    @Override
    public void onLoadMore() {
        UpdateHomeRank();
    }
}
