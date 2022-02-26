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

    private String seconds;
    private String mins;
    private String hours;
    private String daysOfMonth;
    private String month;
    private String daysOfWeek;

    public CronUtil(Date pDate) {
        this.mDate = pDate;
        mCal = Calendar.getInstance();
        this.generateCronExpression();
    }

    private void generateCronExpression() {
        mCal.setTime(mDate);

        this.seconds = String.valueOf(mCal.get(Calendar.SECOND));
        this.hours = String.valueOf(mCal.get(Calendar.HOUR_OF_DAY));
        this.mins = String.valueOf(mCal.get(Calendar.MINUTE));
        this.daysOfMonth = String.valueOf(mCal.get(Calendar.DAY_OF_MONTH));
        this.month = new java.text.SimpleDateFormat("MM").format(mCal.getTime());
        this.daysOfWeek = String.valueOf(DAY_OF_WEEK_NAMES[mCal.get(Calendar.DAY_OF_WEEK) - 1]);
    }

//    public String getCron() {
//        return String.format(
//                "%s %s %s %s %s %s",
//                this.getSeconds(),
//                this.getMins(),
//                this.getHours(),
//                this.getDaysOfMonth(),
//                this.getMonths(),
//                this.getDaysOfWeek()
//        );
//    }

    public String getSeconds() {
        return seconds;
    }

    public String getMins() {
        return mins;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public String getHours() {
        return hours;
    }

    public String getDaysOfMonth() {
        return daysOfMonth;
    }

    public String getMonth() {
        return month;
    }
}
