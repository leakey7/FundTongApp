package com.gzyslczx.ncfundscreenapp.tools;

import android.text.TextUtils;
import android.util.Log;

public class TokenTool {

    private final String TAG = "TokenTool";
    private static volatile TokenTool mTokenTool;

    private TokenTool() {
    }

    /*
    * 双锁单例
    * */
    public static TokenTool Instance(){
        if (mTokenTool==null){
            synchronized (TokenTool.class){
                if (mTokenTool==null){
                    mTokenTool = new TokenTool();
                }
            }
        }
        return mTokenTool;
    }

    /*
     * 检查Token是否有效
     * 返回true有效，false失效
     * */
    public boolean CheckTokenEffective(){
        Log.d(TAG, "检查Token有效性");
        if (TextUtils.isEmpty(SpTool.GetInfo(SpUtils.SpToken))){
            Log.d(TAG, "Token不存在");
            return false;
        }else {
            //判断Token时效
            return !(DateTool.Instance().OverTimeForHour(SpTool.GetInfo(SpUtils.SpTokenTime), 22));
        }
    }

}
