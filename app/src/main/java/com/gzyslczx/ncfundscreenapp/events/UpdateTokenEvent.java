package com.gzyslczx.ncfundscreenapp.events;

public class UpdateTokenEvent {

    private boolean isSuccess;
    private String token;

    public UpdateTokenEvent(boolean isSuccess, String token) {
        this.isSuccess = isSuccess;
        if (isSuccess) {
            this.token = token;
        }
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getToken() {
        return token;
    }

}
