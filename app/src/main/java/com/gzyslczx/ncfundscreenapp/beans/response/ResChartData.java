package com.gzyslczx.ncfundscreenapp.beans.response;

public class ResChartData {

    private boolean IsSuccess;
    private String Message;
    private ResChartDataObj ResultObj;

    public boolean isSuccess() {
        return IsSuccess;
    }

    public String getMessage() {
        return Message;
    }

    public ResChartDataObj getResultObj() {
        return ResultObj;
    }
}
