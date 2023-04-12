package com.example.lzzll.javastudy.demo;

import com.example.lzzll.javastudy.common.util.DateUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author lf
 * @Date 2019/10/23 14:34
 * @Description:
 */
public class TimeTest {

    @Test
    public void localDateTimeTest(){
        System.out.println(new Date().getTime());
    }

    @Test
    public void test1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timeStr = "02:00:00";
        Date date = sdf.parse(timeStr);
        long time = date.getTime();
        System.out.println(time);
        Date date1 = DateUtil.addDateHours(new Date(), 2);
        System.out.println(date1.getTime());
    }

    @Test
    public void test2(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR,2);
        Date time = calendar.getTime();
        System.out.println(time.getTime());
    }

    @Test
    public void test3(){
//        Create date
        LocalDate localDate = LocalDate.now();
        System.out.println("The local date is :: " + localDate);
        //Find the length of the month. That is, how many days are there for this month.
        System.out.println("The number of days available for this month:: " + localDate.lengthOfMonth());
        //Know the month name
        System.out.println("What is the month name? :: " + localDate.getMonth().name());
        //add 2 days to the today's date.
        System.out.println(localDate.plus(2, ChronoUnit.DAYS));
        //substract 2 days from today
        System.out.println(localDate.minus(2, ChronoUnit.DAYS));
        //Convert the string to date
        System.out.println(localDate.parse("2017-04-07"));
    }

    @Test
    public void test4(){
        //Get local time
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        //Get the hour of the day
        System.out.println("The hour of the day:: " + localTime.getHour());
        //add 2 hours to the time.
        System.out.println(localTime.plus(2, ChronoUnit.HOURS));
        //add 6 minutes to the time.
        System.out.println(localTime.plusMinutes(6));
        //substract 2 hours from current time
        System.out.println(localTime.minus(2, ChronoUnit.HOURS));
    }

    @Test
    public void test5() {
        //Get LocalDateTime object
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        //Find the length of month. That is, how many days are there for this month.
        System.out.println("The number of days available for this month:: " + localDateTime.getMonth().length(true));
        //Know the month name
        System.out.println("What is the month name? :: " + localDateTime.getMonth().name());
        //add 2 days to today's date.
        System.out.println(localDateTime.plus(2, ChronoUnit.DAYS));
        //substract 2 days from today
        System.out.println(localDateTime.minus(2, ChronoUnit.DAYS));
    }


}
