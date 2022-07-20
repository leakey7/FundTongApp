package com.gzyslczx.ncfundscreenapp.beans.response;

public class ResAdvObj {

    private int AdId, Sort, Type;
    private String Title, Img, AppUrl, Remark, AddTime;
    private boolean NeedPara, Login, Active;

    public int getAdId() {
        return AdId;
    }

    public int getSort() {
        return Sort;
    }

    public int getType() {
        return Type;
    }

    public String getTitle() {
        return Title;
    }

    public String getImg() {
        return Img;
    }

    public String getAppUrl() {
        return AppUrl;
    }

    public String getRemark() {
        return Remark;
    }

    public String getAddTime() {
        return AddTime;
    }

    public boolean isNeedPara() {
        return NeedPara;
    }

    public boolean isLogin() {
        return Login;
    }

    public boolean isActive() {
        return Active;
    }
}
