package com.ryzhov.daniil;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatesConverterTest {

    @Test
    public void testConvert1() {
        List<String> datesList = new ArrayList<>(Arrays.asList(
                "2022-01-25T08:00:00",
                "2022-01-25T08:30:00",
                "2022-01-25T09:00:00",
                "2022-01-25T09:30:00",
                "2022-01-26T08:00:00",
                "2022-01-26T08:30:00",
                "2022-01-26T09:00:00",
                "2022-01-26T09:30:00"
        ));
        DatesConverter datesConverter = new DatesConverter();
        String cron = datesConverter.convert(datesList);
        Assert.assertEquals("0 0/30 8/9 25/26 1 TUE/WED", cron);
        System.out.println("Test 1 result: " + cron);
    }

    @Test
    public void testConvert2() {
        List<String> datesList = new ArrayList<>(Arrays.asList(
                "2022-01-24T19:53:00",
                "2022-01-24T19:54:00",
                "2022-01-24T19:55:00",
                "2022-01-24T19:56:00",
                "2022-01-24T19:57:00",
                "2022-01-24T19:58:00",
                "2022-01-24T19:59:00",
                "2022-01-24T20:00:00",
                "2022-01-24T20:01:00",
                "2022-01-24T20:02:00"
        ));
        DatesConverter datesConverter = new DatesConverter();
        String cron = datesConverter.convert(datesList);
        Assert.assertEquals("0 * 19/20 24 1 MON", cron);
        System.out.println("Test 2 result: " + cron);
    }

    @Test
    public void testGetImplementationInfo() {
        DatesConverter datesConverter = new DatesConverter();
        String info = datesConverter.getImplementationInfo();
        System.out.println(info);
    }
}