package com.gzyslczx.ncfundscreenapp.conn;

import com.gzyslczx.ncfundscreenapp.beans.request.ReqJustId;
import com.gzyslczx.ncfundscreenapp.beans.response.ResAdv;
import com.gzyslczx.ncfundscreenapp.beans.response.ResChartData;
import com.gzyslczx.ncfundscreenapp.beans.response.ResToken;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ConnInter {

    //基金通Token
    @GET(ConnPath.FTTokenUrl)
    Observable<ResToken> ReqFundTongToken();

    //基金通广告图
    @POST(ConnPath.FTAdvUrl)
    Observable<ResAdv> ReqFundTongAdvList(@Header(ConnPath.HEAD) String header, @Body ReqJustId req);

    //基金通股票型混合型沪深三百对比数据
    @POST(ConnPath.FTChartUrl)
    Observable<ResChartData> ReqFundTongChartData(@Header(ConnPath.HEAD) String header);

}
