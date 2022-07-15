package com.gzyslczx.ncfundscreenapp.events;

import com.gzyslczx.ncfundscreenapp.tools.DateTool;

public class UpdateTokenEvent {

    private boolean isSuccess;
    private String token, tokenTime;

    public UpdateTokenEvent(boolean isSuccess, String token) {
        this.isSuccess = isSuccess;
        if (isSuccess) {
            this.token = token;
            this.tokenTime = DateTool.Instance().GetTodayForyMdhms();
        }
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getToken() {
        return token;
    }

    public String getTokenTime() {
        return tokenTime;
    }
}
