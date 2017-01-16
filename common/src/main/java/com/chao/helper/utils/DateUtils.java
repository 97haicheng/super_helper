package com.chao.helper.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

/**
 * Created by think on 2017/1/16.
 */
public class DateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 获取下一天的开始时间 00:00:00.000
     *
     * @return long
     */
    public static long getNextDayStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 按照指定的时间格式转换成Date对象
     *
     * @param format  格式字符串
     * @param dateStr 时间字符串
     * @return Date
     */
    public static Date format(String format, String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error("format error!", e);
        }
        return date;
    }

    /**
     * 将日期分解成 年、月、日、时、分、秒的int数组，用于发给前台时间
     *
     * @param date 日期
     * @return int[]
     */
    public static int[] splitDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int[] dates = new int[6];
        dates[0] = c.get(Calendar.YEAR);// 年
        dates[1] = c.get(Calendar.MONTH) + 1;// 月
        dates[2] = c.get(Calendar.DAY_OF_MONTH);// 日
        dates[3] = c.get(Calendar.HOUR_OF_DAY);// 小时
        dates[4] = c.get(Calendar.MINUTE);// 分
        dates[5] = c.get(Calendar.SECOND);// 秒
        return dates;
    }

    /**
     * 判断指定日期是否是指定星期几
     *
     * @param date 日期
     * @param week 周
     * @return boolean
     */
    public static boolean isWeekDay(Date date, int week) {
        boolean flag = false;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (week == getDateToWeek(c)) {
            flag = true;
        }

        return flag;
    }

    /**
     * 判断两个日期是否是同一周
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return boolean
     */
    public static boolean isDiffWeek(Date date1, Date date2) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(getFirstDayOfWeek(date1));
        GregorianCalendar gc1 = (GregorianCalendar) Calendar.getInstance();
        gc1.setTime(getFirstDayOfWeek(date2));
        return gc.getTime().getTime() == gc1.getTime().getTime();
    }

    /**
     * 得到输入日期是一个星期的第几天
     *
     * @param gc 日历
     * @return int
     */
    public static int getDateToWeek(Calendar gc) {
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
            case (Calendar.SUNDAY):
                return 7;
            case (Calendar.MONDAY):
                return 1;
            case (Calendar.TUESDAY):
                return 2;
            case (Calendar.WEDNESDAY):
                return 3;
            case (Calendar.THURSDAY):
                return 4;
            case (Calendar.FRIDAY):
                return 5;
            case (Calendar.SATURDAY):
                return 6;
        }
        return 0;
    }

    /**
     * 得到输入日期这个星期的星期一
     *
     * @param date 日期
     * @return Date
     */
    public static Date getFirstDayOfWeek(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
            case (Calendar.SUNDAY):
                gc.add(Calendar.DATE, -6);
                break;
            case (Calendar.MONDAY):
                gc.add(Calendar.DATE, 0);
                break;
            case (Calendar.TUESDAY):
                gc.add(Calendar.DATE, -1);
                break;
            case (Calendar.WEDNESDAY):
                gc.add(Calendar.DATE, -2);
                break;
            case (Calendar.THURSDAY):
                gc.add(Calendar.DATE, -3);
                break;
            case (Calendar.FRIDAY):
                gc.add(Calendar.DATE, -4);
                break;
            case (Calendar.SATURDAY):
                gc.add(Calendar.DATE, -5);
                break;
        }

        return gc.getTime();
    }

    /**
     * 得到这个日期的最后一秒
     *
     * @param date 日期
     * @return Date
     */
    public static Date getTodayEnd(Date date) {
        String day = new SimpleDateFormat("yyyy-MM-dd").format(date);
        day = day + " 23:59:59";
        Date result = null;
        try {
            result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day);
        } catch (ParseException e) {
            LOGGER.error("ParseException", e);
        }
        return result;
    }

    /**
     * 得到这个日期的第一秒
     *
     * @param date 日期
     * @return Date
     */
    public static Date getTodayStart(Date date) {
        String day = new SimpleDateFormat("yyyy-MM-dd").format(date);
        day = day + " 00:00:00";
        Date result = null;
        try {
            result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day);
        } catch (ParseException e) {
            LOGGER.error("ParseException", e);
        }
        return result;
    }

    /**
     * 获取间隔一定天数的日期
     *
     * @param date 日期
     * @param day  天数
     * @return Date
     */
    public static Date getIntervalDay(Date date, long day) {
        Date result = new Date();
        result.setTime(date.getTime() + day * 1000 * 24 * 60 * 60);
        return result;
    }

    /**
     * 获取间隔一定分钟的日期
     *
     * @param date   日期
     * @param minute 分钟
     * @return Date
     */
    public static Date getIntervalMinute(Date date, long minute) {
        Date result = new Date();
        result.setTime(date.getTime() + minute * 1000 * 60);
        return result;
    }

    /**
     * 获取两个时间的天数差
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return int
     */
    public static int getDateDiff(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            date1 = getTodayEnd(date1);
        } else {
            date2 = getTodayEnd(date2);
        }
        return Math.abs((int) ((date1.getTime() - date2.getTime()) / 1000 / 60 / 60 / 24));
    }

    /**
     * 判断两个日期是否是同一天
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return boolean
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return getDateDiff(date1, date2) == 0;
    }

    /**
     * 获取两个时间的小时差
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return int
     */
    public static int getHourDiff(Date date1, Date date2) {
        return Math.abs((int) ((date1.getTime() - date2.getTime()) / 1000 / 60 / 60));
    }

    /**
     * 获取两个时间的分钟差
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return int
     */
    public static int getMinuteDiff(Date date1, Date date2) {
        return Math.abs((int) ((date1.getTime() - date2.getTime()) / 1000 / 60));
    }

    /**
     * 获取两个时间的秒数差
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return int
     */
    public static int getSecondDiff(Date date1, Date date2) {
        return Math.abs((int) ((date1.getTime() - date2.getTime()) / 1000));
    }

    /**
     * 判断是否是零时
     *
     * @return boolean
     */
    public static boolean isWeeHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0
                && calendar.get(Calendar.SECOND) == 0;
    }

    /**
     * 将时间毫秒格式化为字符串
     *
     * @param format 时间格式
     * @param time   时间毫秒
     * @return String
     */
    public static String format(String format, long time) {
        return new SimpleDateFormat(format).format(new Date(time));
    }

    /**
     * 格式化时间：yyyy-MM-dd HH:mm:ss.SSS
     *
     * @param time 时间
     * @return String
     */
    public static String format(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(time));
    }

    /**
     * 格式化到精确到秒的时间字符串：yyyy-MM-dd HH:mm:ss
     *
     * @param time 时间秒
     * @return String
     */
    public static String formatBySec(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));
    }

    /**
     * 格式化到精确到秒的时间字符串：yyyy-MM-dd
     *
     * @param time 时间秒
     * @return String
     */
    public static String formatByDay(long time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(time));
    }

    /**
     * 解析格式时间字符串
     *
     * @param format 格式
     * @param date   日期
     * @return long
     * @throws java.text.ParseException 解析异常
     */
    public static long parse(String format, String date) throws ParseException {
        return new SimpleDateFormat(format).parse(date).getTime();
    }

    /**
     * 获取下一个偶数整点时间
     *
     * @return long
     */
    public static long getNextEvenTime() {
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.HOUR_OF_DAY) % 2 != 0) {
            cal.add(Calendar.HOUR_OF_DAY, 1);
        } else {
            cal.add(Calendar.HOUR_OF_DAY, 2);
        }
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 根据自定义格式将日期转为字符串，默认为 yyyy-MM-dd
     *
     * @param date   日期
     * @param format 日期字符串格式
     * @return String
     */
    public static String dateFormat(Date date, String format) {
        SimpleDateFormat fmt;

        if (StringUtils.isNotEmpty(format)) {
            fmt = new SimpleDateFormat(format);
        } else {
            fmt = new SimpleDateFormat("yyyy-MM-dd");
        }

        return fmt.format(date);
    }

    /**
     * 获取某一天的0时0分0秒日期
     *
     * @param day 1明天日期，0当天日期，-1昨天日期
     * @return Date
     */
    public static Date getOneDayTime(int day) {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, day);

        calendar.set(Calendar.HOUR_OF_DAY, 0);

        calendar.set(Calendar.MINUTE, 0);

        calendar.set(Calendar.SECOND, 0);

        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取某一天的0时0分0秒日期
     *
     * @param date 1明天日期 0当天日期 -1昨天日期
     * @return Date
     */
    public static Date getDateBeforeOrAfter(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, offset);
        return cal.getTime();
    }

    /**
     * 获取七天前的 0点0分0秒
     *
     * 1明天日期 0当天日期 -1昨天日期
     * @return Date
     */
    public static Date get7TianQianDateTime000() {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal1.add(Calendar.DATE, -7);
        cal1.set(Calendar.HOUR_OF_DAY, 0);  //将小时至0
        cal1.set(Calendar.MINUTE, 0);  //将分钟至0
        cal1.set(Calendar.SECOND,0);  //将秒至0
        cal1.set(Calendar.MILLISECOND, 0);  //将毫秒至0
        return cal1.getTime();
    }
    /**
     * 获取本月 第一天的0点0分0秒
     *
     * 1明天日期 0当天日期 -1昨天日期
     * @return Date
     */
    public static Date getNowMouthFristDay000() {
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.MONTH, 0);
        cal2.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        cal2.set(Calendar.HOUR_OF_DAY, 0);  //将小时至0
        cal2.set(Calendar.MINUTE, 0);  //将分钟至0
        cal2.set(Calendar.SECOND,0);  //将秒至0
        cal2.set(Calendar.MILLISECOND, 0);  //将毫秒至0
        return cal2.getTime();
    }
    /**
     * 获取上月 第一天的0点0分0秒
     *
     * 1明天日期 0当天日期 -1昨天日期
     * @return Date
     */
    public static Date getUpMouthFristDay000() {
        Calendar calendar31 = Calendar.getInstance();
        calendar31.add(Calendar.MONTH, -1);
        calendar31.set(Calendar.DAY_OF_MONTH, 1);
        calendar31.set(Calendar.HOUR_OF_DAY, 0);  //将小时至0
        calendar31.set(Calendar.MINUTE, 0);  //将分钟至0
        calendar31.set(Calendar.SECOND,0);  //将秒至0
        return calendar31.getTime();
    }
    /**
     * 获取上月 最后一天的23点59分59秒
     *
     * 1明天日期 0当天日期 -1昨天日期
     * @return Date
     */
    public static Date getUpMouthLastDay000() {
        Calendar calendar32 = Calendar.getInstance();
        calendar32.set(Calendar.DAY_OF_MONTH, 1);
        calendar32.add(Calendar.DATE, -1);
        calendar32.set(Calendar.HOUR_OF_DAY, 23);  //将小时至0
        calendar32.set(Calendar.MINUTE, 59);  //将分钟至0
        calendar32.set(Calendar.SECOND,59);  //将秒至0
        return calendar32.getTime();
    }

    /**
     * 方法说明：获取当前日期
     * @return yyyy-mm-dd
     */
    public static String getNowDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 方法说明：获取固定日期之前的日期
     * @param dayNum 之前多少天
     * @param theDateStr 指定日期，格式yyyy-mm-dd
     * @return yyyy-mm-dd
     */
    public static String getPreviousDate(String theDateStr, int dayNum){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(theDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(new SimpleTimeZone(8 * 60 * 60 * 1000, "BEIJING"));
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -dayNum);
        return sdf.format(calendar.getTime());
    }

}
