package com.ryzhov.daniil;

import com.ryzhov.daniil.exceptions.DatesToCronConvertException;
import com.ryzhov.daniil.interfaces.DatesToCronConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatesConverter implements DatesToCronConverter {
    private final String author = "Рыжов Даниил Евгеньевич";
    private final String githubLink = "https://github.com/mrzhov/dd-test-task";

    private List<String> seconds = new ArrayList<>();
    private List<String> mins = new ArrayList<>();
    private List<String> hours = new ArrayList<>();
    private List<String> daysOfMonth = new ArrayList<>();
    private List<String> month = new ArrayList<>();
    private List<String> daysOfWeek = new ArrayList<>();

    public DatesConverter() {
    }

    @Override
    public String convert(List<String> datesList) {
        datesList.forEach(strDate -> {
            try {
                CronUtil cronUtil = generateCronExpression(strDate);
                this.seconds.add(cronUtil.getSeconds());
                this.mins.add(cronUtil.getMins());
                this.hours.add(cronUtil.getHours());
                this.daysOfMonth.add(cronUtil.getDaysOfMonth());
                this.month.add(cronUtil.getMonth());
                this.daysOfWeek.add(cronUtil.getDaysOfWeek());
            } catch (DatesToCronConvertException e) {
                e.printStackTrace();
            }
        });
        System.out.println(getSeconds());
        System.out.println(getMins());
        System.out.println(getHours());
        System.out.println(getDaysOfMonth());
        System.out.println(getMonth());
        System.out.println(getDaysOfWeek());
        return "";
    }

    public CronUtil generateCronExpression(String strDate) throws DatesToCronConvertException {
        Date date;
        try {
            date = new SimpleDateFormat(DATE_FORMAT).parse(strDate);
        } catch (ParseException e) {
            throw new DatesToCronConvertException("Date doesn't match pattern.", e);
        }
        return new CronUtil(date);
    }

    @Override
    public String getImplementationInfo() {
        return "Автор: " + this.author + "\n"
                + "Имя класса реализации: " + getClass().getSimpleName() + "\n"
                + "Пакет: " + getClass().getPackage() + "\n"
                + "Ссылка на github: " + this.githubLink;
    }

    public List<String> getSeconds() {
        return seconds;
    }

    public List<String> getMins() {
        return mins;
    }

    public List<String> getHours() {
        return hours;
    }

    public List<String> getDaysOfMonth() {
        return daysOfMonth;
    }

    public List<String> getMonth() {
        return month;
    }

    public List<String> getDaysOfWeek() {
        return daysOfWeek;
    }
}
