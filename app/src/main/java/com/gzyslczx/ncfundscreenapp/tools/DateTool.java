package com.gzyslczx.ncfundscreenapp.tools;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {

    private final String TAG = "DateTool";
    private static SimpleDateFormat yMdhmsFormat;
    private static volatile DateTool mDateTool;


    private DateTool() {
        yMdhmsFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    }

    /*
    * 双锁单例
    * */
    public static DateTool Instance(){
        if (mDateTool==null){
            synchronized (DateTool.class){
                if (mDateTool==null){
                    mDateTool = new DateTool();
                }
            }
        }
        return mDateTool;
    }

    /*
    * 获取当前时间-年月日时分秒
    * */
    public String GetTodayForyMdhms(){
        return yMdhmsFormat.format(new Date());
    }

    /*
    * 判断是否超过hour小时
    * */
    public boolean OverTimeForHour(String oldDate, double hour){
        try {
            long old = yMdhmsFormat.parse(oldDate).getTime();
            long current = new Date().getTime();
            long cha = current-old;
            double result = cha * 1.0 / (1000 * 60 * 60);
            if (result<hour){
                Log.d(TAG, "未超指定时效");
                return false;
            }
        } catch (ParseException e) {
            Log.e(TAG, "oldDate格式错误，无法判断");
            e.printStackTrace();
            return true;
        }
        Log.d(TAG, "已超指定时效");
        return true;
    }

}
