package com.gzyslczx.ncfundscreenapp.presenter;

import android.util.Log;

import com.gzyslczx.ncfundscreenapp.BaseActivity;
import com.gzyslczx.ncfundscreenapp.beans.response.ResToken;
import com.gzyslczx.ncfundscreenapp.conn.ConnTool;
import com.gzyslczx.ncfundscreenapp.fragments.BaseFragment;
import com.gzyslczx.ncfundscreenapp.tools.SpTool;

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
                }else {
                    Log.e(TAG, resToken.getMessage());
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
