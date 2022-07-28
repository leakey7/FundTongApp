package com.gzyslczx.ncfundscreenapp.beans.response;

import java.util.List;

public class ResIconObj {

    private int AdId, Sort;
    private String Title, Img, Remark;
    private List<ResIconType> TList;

    public int getAdId() {
        return AdId;
    }

    public int getSort() {
        return Sort;
    }

    public String getTitle() {
        return Title;
    }

    public String getImg() {
        return Img;
    }

    public String getRemark() {
        return Remark;
    }

    public List<ResIconType> getTList() {
        return TList;
    }
}
