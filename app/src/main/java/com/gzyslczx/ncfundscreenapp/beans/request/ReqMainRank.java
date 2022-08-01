package com.gzyslczx.ncfundscreenapp.beans.request;

public class ReqMainRank {

    private int adid, tid, currentpage, pagesize;

    public ReqMainRank(int adid, int tid, int currentpage, int pagesize) {
        this.adid = adid;
        this.tid = tid;
        this.currentpage = currentpage;
        this.pagesize = pagesize;
    }
}
