package com.gzyslczx.ncfundscreenapp.events;

import com.gzyslczx.ncfundscreenapp.beans.response.ResIconObj;

import java.util.List;

public class IconTabEvent {

    private boolean isSuccess;
    private List<ResIconObj> IconTabObjList;

    public IconTabEvent(boolean isSuccess, List<ResIconObj> iconTabObj) {
        this.isSuccess = isSuccess;
        IconTabObjList = iconTabObj;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public List<ResIconObj> getIconTabObj() {
        return IconTabObjList;
    }
}
