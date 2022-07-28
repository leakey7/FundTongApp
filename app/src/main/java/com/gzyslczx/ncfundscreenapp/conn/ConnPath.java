package com.gzyslczx.ncfundscreenapp.conn;

public class ConnPath {

    /*
    * 域名
    * */
    public static final String FundTongPath = "https://gbb.cffs927.com/fundsystemapi/";

    //头部验证
    public static final String HEAD = "Authorization";
    public static final String HeaderBearer = "Bearer ";

    //Token地址
    public static final String FTTokenUrl = "api/UserInfo/LoginToken?username=C8L0E1N6T&password=a8s9aucobtb8f028";
    //广告图地址
    public static final String FTAdvUrl = "api/UserInfo/GetAdvertInfoList";
    //股票型、混合型、沪深三百地址
    public static final String FTChartUrl = "api/UserInfo/GetFundImgInfo";
    //图标Tab
    public static final String FTTabUrl = "api/UserInfo/GetAdvInfoList";

}
