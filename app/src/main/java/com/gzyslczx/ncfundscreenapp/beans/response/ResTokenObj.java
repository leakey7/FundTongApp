package com.gzyslczx.ncfundscreenapp.beans.response;

public class ResTokenObj {

    private String access_token, token_type;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }
}
