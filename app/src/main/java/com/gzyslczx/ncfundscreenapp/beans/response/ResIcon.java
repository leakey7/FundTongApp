package com.gzyslczx.ncfundscreenapp.beans.response;

import java.util.List;

public class ResIcon {

    private boolean IsSuccess;
    private String Message;
    private List<ResIconObj> ResultObj;

    public boolean isSuccess() {
        return IsSuccess;
    }

    public String getMessage() {
        return Message;
    }

    public List<ResIconObj> getResultObj() {
        return ResultObj;
    }
}
