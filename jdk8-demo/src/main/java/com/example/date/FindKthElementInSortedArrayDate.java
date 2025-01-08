package com.example.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Javen
 * @date 2022/3/17
 */
public class FindKthElementInSortedArrayDate {

    @Test
    public void testSimpleDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        System.out.println(simpleDateFormat.format(new Date()));
        System.out.println(simpleDateFormat.format(System.currentTimeMillis()));
        System.out.println(simpleDateFormat.format(System.currentTimeMillis() - 30 * 60 * 1000)); // 30分钟前的时间
    }

    @Test
    public void jdk8Recommend() {
        // 北京时间
        LocalDateTime localDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
        // 洛杉矶时间
        LocalDateTime localDateTime1 = LocalDateTime.now(ZoneId.of("America/Los_Angeles")).atZone(ZoneId.of("America/Los_Angeles")).toLocalDateTime();
        ZonedDateTime localDateTime2 = LocalDateTime.now().atZone(ZoneId.of("America/Los_Angeles")); //atZone()方法将日期时间与指定时区关联
        LocalDateTime localDateTime4 = LocalDateTime.now(ZoneId.of("+0"));
        System.out.println(localDateTime);
        System.out.println(localDateTime1);
        System.out.println(localDateTime2);
        System.out.println(localDateTime4);
        System.out.println(Instant.now().atZone(ZoneId.of("America/Los_Angeles")));

        // 格式化时间
        System.out.println(localDateTime1.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(localDateTime1.minusMinutes(30).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // 解析时间字符串
        System.out.println(LocalDateTime.parse("2020-12-08 17:30:50", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(LocalDateTime.parse("2011-12-08T17:30:50")); // 默认ISO_LOCAL_DATE_TIME
        // 构造时间
        LocalDateTime localDateTime3 = LocalDateTime.of(2020, 12, 31, 8, 50, 30);
        System.out.println(localDateTime3.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // 增加减少时间
        System.out.println(localDateTime3.minus(2, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // 修改指定时间
        System.out.println(localDateTime3.withYear(2003).withMonth(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(localDateTime3.with(TemporalAdjusters.lastDayOfYear()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // 获取指定时间
        System.out.println(localDateTime3.getDayOfYear());
        // 获取两个时间相差的年月日等
        System.out.println(LocalDateTime.now().until(localDateTime3, ChronoUnit.MONTHS));
        // 转换成LocalDate与LocalTime
        System.out.println(localDateTime3.toLocalDate());
        System.out.println(localDateTime3.toLocalTime());

        // 与Date之间的互转
        Date from = Date.from(localDateTime3.atZone(ZoneId.systemDefault()).toInstant());
        LocalDateTime to = LocalDateTime.ofInstant(from.toInstant(), ZoneId.systemDefault());

        // 与Calendar之间的互转
        GregorianCalendar from1 = GregorianCalendar.from(localDateTime3.atZone(ZoneId.systemDefault()));
        LocalDateTime to1 = LocalDateTime.ofInstant(from1.toInstant(), ZoneId.systemDefault());

        // Date与Calendar之间的互转
        Calendar calendar = Calendar.getInstance();
        Date from2 = calendar.getTime();
        calendar.setTime(from2);
    }

    @Test
    public void testCalendar() {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.getTime());
        instance.set(Calendar.MINUTE, instance.get(Calendar.MINUTE) - 30);
        System.out.println(instance.getTime());
    }

    @Test
    // Instant 代表时刻(moment)，时间线中的特定点，它是时区无关的。一般适合替换传统的“java.util.Date"和“java.sql.Timestamp"，用来表示时间戳记。
    // LocalDateTime 表示日期和时间(默认是本地)，因为不具备时区和UTC偏移的概念，所以无法代表一个特定的时刻，通常被视为年月日和小时分钟秒。一般可以描述像生日的日期，以及在墙上时钟上看到的本地时间。
    // LocalDateTime 表示没有时区信息的日期和时间，它不能直接转换为时间戳，除非你将其与时区结合使用（例如通过 ZonedDateTime）。
    // ZonedDateTime 包含时区信息的日期和时间，它更类似于 Calendar，因为 Calendar 也包含时区信息。
    public void testInstant() {
        // Instant时区默认UTC 2023-12-12T14:32:23.565689800Z
        //  结果的Z表示ZoneOffset.UTC（可以进入ZoneID类的318行开始查看：JDK1.8）
        Instant instant = Instant.now();
        // LocalDateTime时区默认本地时区 2023-12-12T22:32:23.565689800
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(instant);
        System.out.println(localDateTime);
        System.out.println(LocalDateTime.now());
        System.out.println(ZonedDateTime.now());
    }
}
