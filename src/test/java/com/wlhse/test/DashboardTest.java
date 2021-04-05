package com.wlhse.test;

import com.wlhse.util.DashboardDateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author tobing
 * @Date 2020/11/25 19:42
 * @Description Dashboard相关测试
 */
public class DashboardTest {


    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date monthMaxDay = DashboardDateUtils.getMaxMonthDay(calendar);
        Date monthMinDay = DashboardDateUtils.getMinMonthDay(calendar);
//        Date yearMaxDay = DashboardDateUtils.getMaxYearDay(calendar);
//        Date yearMinDay = DashboardDateUtils.getMinYearDay(calendar);
//        System.out.println(sdf.format(yearMaxDay));
//        System.out.println(sdf.format(yearMinDay));
        System.out.println(sdf.format(monthMaxDay));
        System.out.println(sdf.format(monthMinDay));
//        Date maxQuarterDay = DashboardDateUtils.getMaxQuarterDay(calendar);
//        Date minQuarterDay = DashboardDateUtils.getMinQuarterDay(calendar);
//        System.out.println(sdf.format(maxQuarterDay));
//        System.out.println(sdf.format(minQuarterDay));


    }
}
