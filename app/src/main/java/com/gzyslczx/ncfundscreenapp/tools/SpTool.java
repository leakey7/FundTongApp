package com.gzyslczx.ncfundscreenapp.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SpTool {

    private static SharedPreferences mSharedPreferences;
    private static volatile SpTool mSpTool;


    private SpTool(Context context) {
        mSharedPreferences = context.getSharedPreferences(SpUtils.SpName, Context.MODE_PRIVATE);
    }

    /*
    * 双锁单例
    * */
    public static SpTool Instance(Context context){
        if (mSharedPreferences==null || mSpTool==null){
            synchronized (SpTool.class){
                if (mSharedPreferences==null || mSpTool==null){
                    mSpTool = new SpTool(context);
                    Log.d(SpUtils.TAG, "初始化SpTool成功");
                }
            }
        }
        return mSpTool;
    }

    /*
    * 存储信息
    * */
    public static boolean SaveInfo(String key, String value){
        if (mSharedPreferences!=null){
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(key, value);
            return editor.commit();
        }else {
            Log.e(SpUtils.TAG, "存储失败。SpTool未初始化");
            return false;
        }
    }

    /*
    * 获取信息
    * */
    public static String GetInfo(String key){
        if (mSharedPreferences!=null){
            String result = mSharedPreferences.getString(key, SpUtils.SpDef);
            if (result.equals(SpUtils.SpDef)){
                Log.d(SpUtils.TAG, "获取失败，信息不存在");
                return null;
            }
            return result;
        }else {
            Log.e(SpUtils.TAG, "获取失败。SpTool未初始化");
            return null;
        }
    }

    /*
    * 清空信息
    * */
    public static boolean ClearInfo(){
        if (mSharedPreferences!=null){
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.clear();
            return editor.commit();
        }else {
            Log.e(SpUtils.TAG, "清空失败。SpTool未初始化");
            return false;
        }
    }

    /*
    * 清除某一信息
    * */
    public static boolean RemoveInfo(String key){
        if (mSharedPreferences!=null){
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.remove(key);
            return editor.commit();
        }else {
            Log.e(SpUtils.TAG, "清除某信息失败。SpTool未初始化");
            return false;
        }
    }

    /*
    * 更新Token及当前时间
    * */
    public static boolean UpdateTokenAndTime(String token){
        if (mSharedPreferences!=null){
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(SpUtils.SpToken, token);
            editor.putString(SpUtils.SpTokenTime, DateTool.Instance().GetTodayForyMdhms());
            return editor.commit();
        }else {
            Log.e(SpUtils.TAG, "更新Token失败。SpTool未初始化");
            return false;
        }
    }

}
