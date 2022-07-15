package com.gzyslczx.ncfundscreenapp.conn;

import com.gzyslczx.ncfundscreenapp.beans.response.ResToken;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ConnInter {

    //基金通Token
    @GET(ConnPath.FTTokenUrl)
    Observable<ResToken> ReqFundTongToken();



}
