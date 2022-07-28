package com.gzyslczx.ncfundscreenapp.conn;

import android.util.Log;

import com.gzyslczx.ncfundscreenapp.BaseActivity;
import com.gzyslczx.ncfundscreenapp.beans.request.ReqJustId;
import com.gzyslczx.ncfundscreenapp.beans.response.ResAdv;
import com.gzyslczx.ncfundscreenapp.beans.response.ResChartData;
import com.gzyslczx.ncfundscreenapp.beans.response.ResIcon;
import com.gzyslczx.ncfundscreenapp.beans.response.ResToken;
import com.gzyslczx.ncfundscreenapp.fragments.BaseFragment;
import com.gzyslczx.ncfundscreenapp.tools.SpTool;
import com.gzyslczx.ncfundscreenapp.tools.SpUtils;
import com.trello.rxlifecycle4.android.ActivityEvent;
import com.trello.rxlifecycle4.android.FragmentEvent;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnTool {

    private volatile static ConnTool mConnTool;
    private ConnInter mConnInter;

    private ConnTool() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(ConnPath.FundTongPath)
                .build();
        mConnInter = retrofit.create(ConnInter.class);
    }

    /*
    * 双锁单例
    * */
    public static ConnTool Instance(){
        if (mConnTool==null){
            synchronized (ConnTool.class){
                if (mConnTool==null){
                    mConnTool = new ConnTool();
                }
            }
        }
        return mConnTool;
    }


    /*
     * 附加重试请求
     * */
    private Observable AddRetryReq(Observable observable, String TAG) {
        return observable.retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Throwable {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Throwable {
                        if (throwable instanceof IOException) {
                            Log.d(TAG, String.format("发生异常:%s,2s后重试请求", throwable.getMessage()));
                            return Observable.just(1).delay(2, TimeUnit.SECONDS);
                        }
                        Log.d(TAG, String.format("发生未知异常:%s", throwable.getMessage()));
                        return Observable.error(new Throwable(
                                String.format("发生未知异常:%s", throwable.getMessage())));
                    }
                });
            }
        });
    }

    /*
     * 添加结束项--Activity
     * */
    private Observable AddExtraReqOfAct(Observable observable, String TAG, BaseActivity activity) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY));
    }

    /*
     * 添加结束项--Fragment
     * */
    private Observable AddExtraReqOfFrag(Observable observable, String TAG, BaseFragment fragment) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(fragment.bindUntilEvent(FragmentEvent.DESTROY));
    }

    /*
     * 请求Token
     * flag:0=Activity 1=Fragment
     * */
    public Observable<ResToken> ReqFundTongToken(String TAG, int flag, BaseActivity baseActivity, BaseFragment baseFragment){
        Observable<ResToken> observable = mConnInter.ReqFundTongToken();
        observable = AddRetryReq(observable, TAG);
        if (flag==0) {
            observable = AddExtraReqOfAct(observable, TAG, baseActivity);
        }else {
           observable =  AddExtraReqOfFrag(observable, TAG, baseFragment);
        }
        return observable;
    }

    /*
    * 请求广告图
    * flag：0=Activity 1=Fragment
    * type: 1=首页基金筛选图 2=基金详情小图标
    * */
    public Observable<ResAdv> ReqFundTongAdvList(String TAG, int flag, BaseActivity baseActivity, BaseFragment baseFragment, int type){
        ReqJustId reqJustId = new ReqJustId(type);
        Observable<ResAdv> observable = mConnInter.ReqFundTongAdvList(ConnPath.HeaderBearer + SpTool.GetInfo(SpUtils.SpToken),
                reqJustId);
        observable = AddRetryReq(observable, TAG);
        if (flag==0) {
            observable = AddExtraReqOfAct(observable, TAG, baseActivity);
        }else {
            observable =  AddExtraReqOfFrag(observable, TAG, baseFragment);
        }
        return observable;
    }

    /*
     * 请求股票、混合和沪深三百对比数据
     * flag:0=Activity 1=Fragment
     * */
    public Observable<ResChartData> ReqFundTongChart(String TAG, int flag, BaseActivity baseActivity, BaseFragment baseFragment){
        Observable<ResChartData> observable = mConnInter.ReqFundTongChartData(ConnPath.HeaderBearer + SpTool.GetInfo(SpUtils.SpToken));
        observable = AddRetryReq(observable, TAG);
        if (flag==0) {
            observable = AddExtraReqOfAct(observable, TAG, baseActivity);
        }else {
            observable =  AddExtraReqOfFrag(observable, TAG, baseFragment);
        }
        return observable;
    }

    /*
    * 请求图标Tab
    * */
    public Observable<ResIcon> RequestFundTongTab(String TAG, int flag, BaseActivity baseActivity, BaseFragment baseFragment){
        Observable<ResIcon> observable = mConnInter.ReqFundTongTabIcon(ConnPath.HeaderBearer + SpTool.GetInfo(SpUtils.SpToken));
        observable = AddRetryReq(observable, TAG);
        if (flag==0) {
            observable = AddExtraReqOfAct(observable, TAG, baseActivity);
        }else {
            observable =  AddExtraReqOfFrag(observable, TAG, baseFragment);
        }
        return observable;
    }

}
