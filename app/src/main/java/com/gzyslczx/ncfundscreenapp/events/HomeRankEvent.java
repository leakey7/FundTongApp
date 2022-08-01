package com.gzyslczx.ncfundscreenapp.events;

import com.gzyslczx.ncfundscreenapp.beans.response.ResMainRankObj;

import java.util.List;

public class HomeRankEvent {

    private boolean IsSuccess;
    private ResMainRankObj resMainRankObj;

    public HomeRankEvent(boolean isSuccess, ResMainRankObj resMainRankObj) {
        IsSuccess = isSuccess;
        this.resMainRankObj = resMainRankObj;
    }

    public boolean isSuccess() {
        return IsSuccess;
    }

    public ResMainRankObj getResMainRankObj() {
        return resMainRankObj;
    }
}
