package com.ryzhov.daniil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
//        List<String> datesList = new ArrayList<>(Arrays.asList(
//                "2022-01-25T08:00:00",
//                "2022-01-25T08:30:00",
//                "2022-01-25T09:00:00",
//                "2022-01-25T09:30:00",
//                "2022-01-26T08:00:00",
//                "2022-01-26T08:30:00",
//                "2022-01-26T09:00:00",
//                "2022-01-26T09:30:00"
//        ));
        DatesConverter datesConverter = new DatesConverter();
//        datesConverter.convert(datesList);
        System.out.println(datesConverter.getImplementationInfo());
    }
}
