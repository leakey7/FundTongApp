package com.gzyslczx.ncfundscreenapp.beans.response;

import java.util.List;

public class ResChartDataObj {

    //mList一月, m3List三月, m6List六月, yList一年, y2List两年, y3List三年, y5List五年
    private List<ResChartDataInfo> mList, m3List, m6List, yList, y2List, y3List, y5List;

    public List<ResChartDataInfo> getmList() {
        return mList;
    }

    public List<ResChartDataInfo> getM3List() {
        return m3List;
    }

    public List<ResChartDataInfo> getM6List() {
        return m6List;
    }

    public List<ResChartDataInfo> getyList() {
        return yList;
    }

    public List<ResChartDataInfo> getY2List() {
        return y2List;
    }

    public List<ResChartDataInfo> getY3List() {
        return y3List;
    }

    public List<ResChartDataInfo> getY5List() {
        return y5List;
    }
}
