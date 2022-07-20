package com.gzyslczx.ncfundscreenapp.events;

import com.gzyslczx.ncfundscreenapp.beans.response.ResAdvObj;

import java.util.List;

public class AdvEvent {

    private boolean isSuccess;
    private List<ResAdvObj> advObjList;
    private int type;

    public AdvEvent(boolean isSuccess, List<ResAdvObj> advObjList, int type) {
        this.isSuccess = isSuccess;
        this.advObjList = advObjList;
        this.type = type;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public List<ResAdvObj> getAdvObjList() {
        return advObjList;
    }

    public int getType() {
        return type;
    }
}
