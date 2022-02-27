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
        Assert.assertEquals("", datesConverter.convert(datesList));
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
        Assert.assertEquals("", datesConverter.convert(datesList));
    }
}