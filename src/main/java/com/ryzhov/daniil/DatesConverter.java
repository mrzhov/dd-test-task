package com.ryzhov.daniil;

import com.ryzhov.daniil.exceptions.DatesToCronConvertException;
import com.ryzhov.daniil.interfaces.DatesToCronConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DatesConverter implements DatesToCronConverter {
    private final String[] DAY_OF_WEEK_NAMES = new String[]{
            "SUN",
            "MON",
            "TUE",
            "WED",
            "THU",
            "FRI",
            "SAT"
    };

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
        Map<String, Set<Integer>> datesMap = new HashMap<String, Set<Integer>>() {{initMap(this);}};
        Map<String, String> cronMap = new LinkedHashMap<String, String>() {{initMap(this);}};

        datesList.forEach(strDate -> {
            try {
                CronUtil cronUtil = generateCronExpression(strDate);
                for (String key : datesMap.keySet()) {
                    putValueInDatesMap(datesMap, key, cronUtil.getValueByFieldName(key));
                }
            } catch (DatesToCronConvertException e) {
                e.printStackTrace();
            }
        });
        for (String key : datesMap.keySet()) {
            Set<Integer> elDateSet = datesMap.get(key);
            cronMap.put(key, transformDatesSetToCronEl(elDateSet));
        }
        transformDayOfWeekNumbersToNames(cronMap);
        return transformCronMapToCron(cronMap);
    }

    private <T> void initMap(HashMap<String, T> hashMap) {
        hashMap.put("seconds", null);
        hashMap.put("mins", null);
        hashMap.put("hours", null);
        hashMap.put("daysOfMonth", null);
        hashMap.put("month", null);
        hashMap.put("daysOfWeek", null);
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

    private void putValueInDatesMap(Map<String, Set<Integer>> datesMap, String key, Integer value) {
        Set<Integer> itemsList;
        if (datesMap.get(key) == null) itemsList = new TreeSet<>();
        else itemsList = datesMap.get(key);
        itemsList.add(value);
        datesMap.put(key, itemsList);
    }

    private String transformDatesSetToCronEl(Set<Integer> set) {
        String cronEl = null;
        if (set.size() == 1) {
            Optional<Integer> result = set.stream().findFirst();
            cronEl = String.valueOf(result.get());
        }
        if (set.size() == 2) {
            cronEl = set.stream().map(Object::toString).collect(Collectors.joining("/"));
        }
        if (set.size() > 2) {
            if (!checkIfNumbersIsOrder(set)) cronEl = "*";
            else {
                String firstValue = String.valueOf(set.stream().findFirst().get());
                String lastValue = String.valueOf(set.stream().reduce((first, second) -> second).get());
                cronEl = firstValue + "-" + lastValue;
            }
        }
        return cronEl;
    }

    private void transformDayOfWeekNumbersToNames(Map<String, String> cronMap) {
        String separator = null;
        String daysOfWeekWithNames = null;
        String numbers = cronMap.get("daysOfWeek");
        if (numbers.contains("/")) separator = "/";
        if (numbers.contains("-")) separator = "-";

        if (separator != null) {
            String[] values = numbers.split(separator);
            daysOfWeekWithNames = Arrays.stream(values)
                    .map(v -> DAY_OF_WEEK_NAMES[Integer.parseInt(v)])
                    .collect(Collectors.joining(separator));
        } else {
            daysOfWeekWithNames = DAY_OF_WEEK_NAMES[Integer.parseInt(numbers)];
        }
        cronMap.put("daysOfWeek", daysOfWeekWithNames);
    }

    private boolean checkIfNumbersIsOrder(Set<Integer> set) {
        Integer prevNum = null;
        boolean flag = true;
        for (Integer num : set) {
            if (prevNum == null || (num - prevNum == 1)) prevNum = num;
            else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private String transformCronMapToCron(Map<String, String> cronMap) {
        return String.join(" ", cronMap.values());
    }
}
