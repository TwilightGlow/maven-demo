package com.example.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Javen
 * @date 2022/3/17
 */
public class TestDate {

    @Test
    public void testSimpleDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        System.out.println(simpleDateFormat.format(new Date()));
        System.out.println(simpleDateFormat.format(System.currentTimeMillis()));
        System.out.println(simpleDateFormat.format(System.currentTimeMillis() - 30 * 60 * 1000)); // 30分钟前的时间
    }

    @Test
    public void jdk8Recommend() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(LocalDateTime.now().minusMinutes(30).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Test
    public void testCalendar() {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.getTime());
        instance.set(Calendar.MINUTE, instance.get(Calendar.MINUTE) - 30);
        System.out.println(instance.getTime());

    }
}
