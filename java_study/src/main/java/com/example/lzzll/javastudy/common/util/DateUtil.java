package com.example.lzzll.javastudy.common.util;

import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 日期处理
 *
 * @author zfan
 */
public class DateUtil {

	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public final static String MONTH_PATTERN = "yyyyMM";
    /** 时间格式(yyyy-MM-dd) */
    public final static String EXPORT_DATE_PATTERN = "yyyy/MM/dd";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }
    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
	public static String formatTime(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }


    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtil.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd HH:mm:ss
     * @param strDate  yyyy-MM-dd HH:mm:ss 字符串
     * @return  返回Date
     */
    public static Date parseTime(String strDate) {
        return stringToDate(strDate, DATE_TIME_PATTERN);
    }

    /**
     * 将指定时间从一个时间格式转换成另一个时间
     * @param strDate 时间字符串
     * @param patternSource 开始的时间格式
     * @param patternTarget 转换后的时间格式
     * @return
     */
    public static String formatPatternToAnother(String strDate,String patternSource,String patternTarget){
        Date date = stringToDate(strDate, patternSource);
        return format(date,patternTarget);
    }


    /**
     * 返回当月最后一天
     * @param date  日期
     * @return  返回Date
     */
    public static Date getLastTimeOfMonth(Date date) {
        cn.hutool.core.date.DateTime dateTime = cn.hutool.core.date.DateUtil.endOfMonth(date);
        return dateTime.toJdkDate();
    }

    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtil.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)){
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return  返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date 日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date 日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date 日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date 日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     * 将当前时间转换为可以在数据库中国存储的"yyyy-MM-dd HH:mm:ss"格式数据
     * @return
     */
    public static Timestamp nowToTimeStamp(){
        return new Timestamp(System.currentTimeMillis());
    }

    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }

    // 获得某天最大时间 2017-10-15 23:59:59
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 获得某天最小时间 2017-10-15 00:00:00
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getEndOfString(String date) {
     Date date1 =stringToDate(date,DateUtil.DATE_TIME_PATTERN);
       return getEndOfDay(date1);
    }

    public static String getEndOfString(String dateStr,String pattern) {
        Date date1 =stringToDate(dateStr,pattern);
        Date date = getEndOfDay(date1);
        return format(date,DATE_TIME_PATTERN);
    }


    public static Date getStartOfString(String date) {
        Date date1 =stringToDate(date,DateUtil.DATE_TIME_PATTERN);
        return getStartOfDay(date1);
    }

    public static int compare(Date date,Date other) {
        long first = date.getTime();
        long second = other.getTime();
        if(first == second){
            return 0;
        }else if(first > second){
            return 1;
        }else{
            return -1;
        }
    }

    public static int getIntMonth(Date date){
        String format = format(date, MONTH_PATTERN);
        return Integer.parseInt(format);
    }
    //获取本月最开始的时间
    public static String getBeginOfTHisMonth(){
     return formatTime(cn.hutool.core.date.DateUtil.beginOfMonth(new Date()).toJdkDate());
    }

    public static String getStartTime(int month){
        String sm = month+"";
        String temp = sm.substring(0,4)+"-"+sm.substring(4,6)+"-"+"01 00:00:00";
        return temp;
    }

    public static String getEndTime(int month){
        Date lastTimeOfMonth = getLastTimeOfMonth(parseTime(getStartTime(month)));
        return formatTime(lastTimeOfMonth);
    }

    /**
     * 将2021-03-01格式的起止时间转化为"2020/12/1-2020/12/31"格式
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getDateStr(String startDate, String endDate) {
        if (!StringUtil.isEmpty(startDate) && !StringUtil.isEmpty(endDate)){
            String exportStartDateStr = DateUtil.formatPatternToAnother(startDate, DATE_PATTERN, EXPORT_DATE_PATTERN);
            String exportEndDateStr = DateUtil.formatPatternToAnother(endDate, DATE_PATTERN, EXPORT_DATE_PATTERN);
            return exportStartDateStr+"-"+exportEndDateStr;
        }
        return null;
    }


    public static void main(String[] args) {
//        Date lastTimeOfMonth = getLastTimeOfMonth(new Date(System.currentTimeMillis()+546546523252L));
//        System.out.println(format(lastTimeOfMonth,DATE_TIME_PATTERN));
//        System.out.println(getIntMonth(lastTimeOfMonth));
//        System.out.println( formatTime(cn.hutool.core.date.DateUtil.beginOfMonth(new Date()).toJdkDate()));
//        System.out.println(getStartTime(202006));
//        System.out.println(getEndTime(202006));

        System.out.println(formatPatternToAnother("2020-12-24",DATE_PATTERN,DATE_TIME_PATTERN));
        System.out.println(getEndOfString("2020-12-24",DATE_PATTERN));
    }

}
