package com.ryzhov.daniil;

import java.util.Calendar;
import java.util.Date;

public class CronUtil {
    private final Date mDate;
    private final Calendar mCal;
    private final String[] DAY_OF_WEEK_NAMES = new String[]{
            "SUN",
            "MON",
            "TUE",
            "WED",
            "THU",
            "FRI",
            "SAT"
    };

    private Integer seconds;
    private Integer mins;
    private Integer hours;
    private Integer daysOfMonth;
    private Integer month;
    private Integer daysOfWeek;

    public CronUtil(Date pDate) {
        this.mDate = pDate;
        mCal = Calendar.getInstance();
        this.generateCronExpression();
    }

    public Integer getSeconds() {
        return seconds;
    }

    public Integer getMins() {
        return mins;
    }

    public Integer getHours() {
        return hours;
    }

    public Integer getDaysOfMonth() {
        return daysOfMonth;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getDaysOfWeek() {
        return daysOfWeek;
    }

    public Integer getValueByFieldName(String key) {
        switch (key) {
            case "seconds": return this.getSeconds();
            case "mins": return this.getMins();
            case "hours": return this.getHours();
            case "daysOfMonth": return this.getDaysOfMonth();
            case "month": return this.getMonth();
            case "daysOfWeek": return this.getDaysOfWeek();
        }
        return null;
    }

    private void generateCronExpression() {
        mCal.setTime(mDate);

        this.seconds = mCal.get(Calendar.SECOND);
        this.hours = mCal.get(Calendar.HOUR_OF_DAY);
        this.mins = mCal.get(Calendar.MINUTE);
        this.daysOfMonth = mCal.get(Calendar.DAY_OF_MONTH);
        this.month = mCal.get(Calendar.MONTH) + 1;
        this.daysOfWeek = mCal.get(Calendar.DAY_OF_WEEK) - 1;
//        this.daysOfWeek = String.valueOf(DAY_OF_WEEK_NAMES[mCal.get(Calendar.DAY_OF_WEEK) - 1]);
    }
}
