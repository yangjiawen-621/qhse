package com.wlhse.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author tobing
 * @Date 2020/11/25 22:18
 * @Description 大屏时间工具类
 */
public class DashboardDateUtils {


    private static final int MAX_MONTH = Calendar.DECEMBER;
    private static final int MIN_MONTH = Calendar.JANUARY;
    private static final int MAX_YEAR_DAY = 31;
    private static final int MIN_YEAR_DAY = 1;
    private static final int MIN_QUARTER_DAY = 1;


    /**
     * 获取一个月的最大时间
     *
     * @param calendar cal
     * @return Date
     */
    public static Date getMaxMonthDay(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR) - 1900;
        int month = calendar.get(Calendar.MONTH);
        int maxMonthDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date monthMaxDay = new Date(year, month, maxMonthDay);
        return monthMaxDay;
    }

    /**
     * 获取一个当前月的最小日期
     *
     * @param calendar cal
     * @return Date
     */
    public static Date getMinMonthDay(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR) - 1900;
        int month = calendar.get(Calendar.MONTH);
        int minMonthDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        Date monthMinDay = new Date(year, month, minMonthDay);
        return monthMinDay;
    }

    /**
     * 获取当前年份的最大日期
     *
     * @param calendar cal
     * @return Date
     */
    public static Date getMaxYearDay(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR) - 1900;
        Date yearMaxDay = new Date(year, MAX_MONTH, MAX_YEAR_DAY);
        return yearMaxDay;
    }

    /**
     * 获取当前年份的最小日期
     *
     * @param calendar cal
     * @return Date
     */
    public static Date getMinYearDay(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR) - 1900;
        Date yearMinDay = new Date(year, MIN_MONTH, MIN_YEAR_DAY);
        return yearMinDay;
    }

    /**
     * 获取当前季度的最小日期
     *
     * @param calendar cal
     * @return Date
     */
    public static Date getMinQuarterDay(Calendar calendar) {
        int currentYear = calendar.get(Calendar.YEAR) - 1900;
        int currentMonth = calendar.get(Calendar.MONTH);
        int minMonth = Calendar.MARCH;
        // 第一季度: 1-3
        // 第二季度: 4-6
        // 第三季度: 7-9
        // 第四季度: 10-12
        if (currentMonth >= Calendar.JANUARY && currentMonth <= Calendar.MARCH) {
            minMonth = Calendar.JANUARY;
        } else if (currentMonth >= Calendar.APRIL && currentMonth <= Calendar.JUNE) {
            minMonth = Calendar.APRIL;
        } else if (currentMonth >= Calendar.JULY && currentMonth <= Calendar.SEPTEMBER) {
            minMonth = Calendar.JULY;
        } else {
            minMonth = Calendar.OCTOBER;
        }
        return new Date(currentYear, minMonth, MIN_QUARTER_DAY);
    }


    /**
     * 获取当前季度的最大日期
     *
     * @param calendar cal
     * @return Date
     */
    public static Date getMaxQuarterDay(Calendar calendar) {
        int currentYear = calendar.get(Calendar.YEAR) - 1900;
        int currentMonth = calendar.get(Calendar.MONTH);
        int maxMonth = Calendar.MARCH;
        // 第一季度: 1-3
        // 第二季度: 4-6
        // 第三季度: 7-9
        // 第四季度: 10-12
        if (currentMonth >= Calendar.JANUARY && currentMonth <= Calendar.MARCH) {
            maxMonth = Calendar.MARCH;
        } else if (currentMonth >= Calendar.APRIL && currentMonth <= Calendar.JUNE) {
            maxMonth = Calendar.JUNE;
        } else if (currentMonth >= Calendar.JULY && currentMonth <= Calendar.SEPTEMBER) {
            maxMonth = Calendar.SEPTEMBER;
        } else {
            maxMonth = Calendar.DECEMBER;
        }
        calendar.set(Calendar.MONTH, maxMonth);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 回置当前月份
        calendar.set(Calendar.MONTH, currentMonth);
        return new Date(currentYear, maxMonth, maxDay);
    }


}
