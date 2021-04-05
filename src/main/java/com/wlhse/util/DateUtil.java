package com.wlhse.util;

import com.wlhse.util.state_code.CodeDict;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String getTime(int type) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String result = null;
        if (type == CodeDict.TIME_FIRSTDAY) {
            result = sdf.format(date) + "-1-1";
        } else if (type == CodeDict.TIME_LASTDAY) {
            result = sdf.format(date) + "-12-31";
        }
        return result;
    }

    public static String getThisWeekLastDay() {
        Calendar cal = Calendar.getInstance();
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String imptimeBegin = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        return imptimeEnd;
    }

    public static String getLastWeekFisrtDay() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setFirstDayOfWeek(Calendar.MONDAY);
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        int offset1 = 1 - dayOfWeek;
        calendar1.add(Calendar.DATE, offset1 - 7);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastBeginDate = sdf.format(calendar1.getTime());
        return lastBeginDate;
    }

    public static String getLastWeekLastDay() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset2 = 7 - dayOfWeek;
        calendar.add(Calendar.DATE, offset2 - 7);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastEndDate = sdf.format(calendar.getTime());
        return lastEndDate;
    }

    public static boolean compareDateInOneWeek(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        boolean result = false;
        try {
            Date date3 = format.parse(date1);
            Date date4 = format.parse(date2);
            if (date3.after(date4)) {
                result = false;
            } else {
                result = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int dayForWeek(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public static String geLastWeekMonday() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String imptimeEnd = sdf.format(cal.getTime());
        return imptimeEnd;
    }

}
