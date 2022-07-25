package com.gzyslczx.ncfundscreenapp.presenter;

import android.util.Log;

import com.gzyslczx.ncfundscreenapp.beans.response.ResAdv;
import com.gzyslczx.ncfundscreenapp.beans.response.ResChartData;
import com.gzyslczx.ncfundscreenapp.conn.ConnTool;
import com.gzyslczx.ncfundscreenapp.events.AdvEvent;
import com.gzyslczx.ncfundscreenapp.fragments.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

public class HomeFragPres extends BasePresenter{

    /*
    * 请求基金筛选图
    * */
    public void RequestFundSelectPic(String TAG, BaseFragment baseFragment){
        RequestAdvList(TAG, 1, null, baseFragment, 1);
    }

    /*
    * 请求沪深三百对比图数据
    * */
    public void RequestChartData(String TAG, BaseFragment baseFragment){
        Observable<ResChartData> observable = ConnTool.Instance().ReqFundTongChart(TAG, 1, null, baseFragment);
        observable.subscribe(new Consumer<ResChartData>() {
            @Override
            public void accept(ResChartData resChartData) throws Throwable {
                if (resChartData.isSuccess()){

                }else {

                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {

            }
        });
    }

    /*
    * 请求
    * */



}
