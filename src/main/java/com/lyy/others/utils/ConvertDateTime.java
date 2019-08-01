package com.lyy.others.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liyueyang on 2019/8/1.
 */
public class ConvertDateTime {
    private static ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String calender2Str(Calendar calendar){
        if (null == calendar)return null;
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) df.get();
        String s = simpleDateFormat.format(calendar.getTime());
        return s;
    }

    /**
     * 获取现在时间
     * @return返回字符串格式yyyyMMdd
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);
        //System.out.println("TIME:::"+dateString);
        return dateString;
    }

    /**
     * 获取指定天数的时间 yyyy-MM-dd HH:mm:ss
     *     -1 ：昨天;0 ：今天;1 ：明天
     */
    public static String getSpecificDate(int day){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeFormat = sdf.format(c.getTime());
        return timeFormat;
    }

}
