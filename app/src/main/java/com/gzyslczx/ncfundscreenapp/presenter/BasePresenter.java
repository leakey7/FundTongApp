package com.gzyslczx.ncfundscreenapp.presenter;

import android.util.Log;

import com.gzyslczx.ncfundscreenapp.BaseActivity;
import com.gzyslczx.ncfundscreenapp.beans.response.ResAdv;
import com.gzyslczx.ncfundscreenapp.beans.response.ResToken;
import com.gzyslczx.ncfundscreenapp.conn.ConnTool;
import com.gzyslczx.ncfundscreenapp.events.AdvEvent;
import com.gzyslczx.ncfundscreenapp.events.UpdateTokenEvent;
import com.gzyslczx.ncfundscreenapp.fragments.BaseFragment;
import com.gzyslczx.ncfundscreenapp.tools.SpTool;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

public class BasePresenter {

    /*
    * 更新并存储Token
    * */
    public void RequestUpdateToken(String TAG, int flag, BaseActivity baseActivity, BaseFragment baseFragment){
        Observable<ResToken> observable = ConnTool.Instance().ReqFundTongToken(TAG, flag, baseActivity, baseFragment);
        observable.subscribe(new Consumer<ResToken>() {
            @Override
            public void accept(ResToken resToken) throws Throwable {
                if (resToken.isSuccess()){
                    SpTool.UpdateTokenAndTime(resToken.getResultObj().getAccess_token());
                    EventBus.getDefault().post(new UpdateTokenEvent(true, resToken.getResultObj().getAccess_token()));
                }else {
                    Log.e(TAG, resToken.getMessage());
                    EventBus.getDefault().post(new UpdateTokenEvent(false, null));
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    /*
    * 请求广告图
    * type: 1=基金筛选图  2=基金详情小图标
    * */
    public void RequestAdvList(String TAG, int flag, BaseActivity baseActivity, BaseFragment baseFragment, int type){
        Observable<ResAdv> observable = ConnTool.Instance().ReqFundTongAdvList(TAG, flag, baseActivity, baseFragment, type);
        observable.subscribe(new Consumer<ResAdv>() {
            @Override
            public void accept(ResAdv resAdv) throws Throwable {
                if (resAdv.isSuccess()) {
                    EventBus.getDefault().post(new AdvEvent(true, resAdv.getResultObj(), type));
                }else {
                    Log.e(TAG, resAdv.getMessage());
                    EventBus.getDefault().post(new AdvEvent(false, null, type));
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

}
