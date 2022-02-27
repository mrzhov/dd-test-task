package com.ryzhov.daniil;

import com.ryzhov.daniil.exceptions.DatesToCronConvertException;
import com.ryzhov.daniil.interfaces.DatesToCronConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DatesConverter implements DatesToCronConverter {
    private Map<String, Set<Integer>> datesMap = new HashMap<String, Set<Integer>>() {{
        put("seconds", new TreeSet<>());
        put("mins", new TreeSet<>());
        put("hours", new TreeSet<>());
        put("daysOfMonth", new TreeSet<>());
        put("month", new TreeSet<>());
        put("daysOfWeek", new TreeSet<>());
    }};

    public DatesConverter() {
    }

    @Override
    public String getImplementationInfo() {
        String author = "Рыжов Даниил Евгеньевич";
        String githubLink = "https://github.com/mrzhov/dd-test-task";
        return "Автор: " + author + "\n"
                + "Имя класса реализации: " + getClass().getSimpleName() + "\n"
                + "Пакет: " + getClass().getPackage() + "\n"
                + "Ссылка на github: " + githubLink;
    }

    @Override
    public String convert(List<String> datesList) {
        datesList.forEach(strDate -> {
            try {
                CronUtil cronUtil = generateCronExpression(strDate);
                for (String key: datesMap.keySet()) {
                    putValueInDatesMap(key, cronUtil.getValueByFieldName(key));
                }
            } catch (DatesToCronConvertException e) {
                e.printStackTrace();
            }
        });
        System.out.println(datesMap);
        return "";
    }

    private void putValueInDatesMap(String key, Integer value) {
        Set<Integer> itemsList = datesMap.get(key);
        itemsList.add(value);
        datesMap.put(key, itemsList);
    }

    private CronUtil generateCronExpression(String strDate) throws DatesToCronConvertException {
        Date date;
        try {
            date = new SimpleDateFormat(DATE_FORMAT).parse(strDate);
        } catch (ParseException e) {
            throw new DatesToCronConvertException("Date doesn't match pattern.", e);
        }
        return new CronUtil(date);
    }
}
