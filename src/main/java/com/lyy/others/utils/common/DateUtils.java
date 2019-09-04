package com.lyy.others.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by liYueYang on 2019/8/30.
 */

public class DateUtils {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 某个日期date增加count个自然月
     *
     * @param date  某个日期(yyyy-MM-dd格式)
     * @param count 相加的月数
     * @return
     * @throws ParseException
     */
    public static String addMonth(String date, int count) throws ParseException {
        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int day = Integer.parseInt(date.split("-")[2]);
        if (count + month > 12) {
            year += (count + month) / 12;
            month = (count + month) % 12;
        } else {
            month += count;
        }
        if (day >= 28) {
            if (day >= getDayByMonth(year + "-" + (month - count))) {  //为月底或大于月底
                day = getDayByMonth(year + "-" + month);  //日期变为下个月月底
            }
        }
        return sdf.format(sdf.parse(year + "-" + month + "-" + day));
    }

    /**
     * 获取当前月份的天数
     *
     * @param date
     * @return
     */
    public static int getDayByMonth(String date) {
        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int days[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (2 == month && 0 == (year % 4) && (0 != (year % 100) || 0 == (year % 400))) {
            days[1] = 29;
        }
        return Integer.valueOf(days[month - 1]);
    }

    public static void main(String[] args) throws ParseException {

        String date = "2019-01-29";
        Integer i = 1;
        System.out.println("获得" + date + "加" + i + "个自然月时间：" + addMonth(date, i));
    }
}

