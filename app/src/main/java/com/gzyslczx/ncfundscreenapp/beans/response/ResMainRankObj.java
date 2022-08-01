package com.gzyslczx.ncfundscreenapp.beans.response;

import java.util.List;

public class ResMainRankObj {

    private int PageSize, CurrentPage, PageCount, RecordCount;
    private List<ResMainRankInfo> PageInfo;

    public int getPageSize() {
        return PageSize;
    }

    public int getCurrentPage() {
        return CurrentPage;
    }

    public int getPageCount() {
        return PageCount;
    }

    public int getRecordCount() {
        return RecordCount;
    }

    public List<ResMainRankInfo> getPageInfo() {
        return PageInfo;
    }
}
