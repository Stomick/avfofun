package com.team.noty.event.utils;

import android.content.Context;

import com.team.noty.event.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormatter {

    private static final String days = "1234567";
    private static final String dateFormat = "yyyy-MM-dd";
    private static final String timeFormat = "HH:mm";
    private static final String dateTimeFormat = dateFormat + " " + timeFormat;

    public static String yesterday = "yesterday";
    public static String today = "today";

    public static boolean differenceLessMinute(Date one, Date two) {
        return Math.abs(getMinutes(one) - getMinutes(two)) == 0;
    }

    public static String getNearestDate(List<String> days) {
        Calendar date = nextDayOfWeek(DateFormatter.days.indexOf(days.get(0)));
        for (String d : days) {
            Calendar current = nextDayOfWeek(DateFormatter.days.indexOf(d));
            if (date.compareTo(current) > 0)
                date = current;
        }
        return String.valueOf(date.get(Calendar.YEAR))
                + String.valueOf(date.get(Calendar.MONTH) + 1)
                + String.valueOf(date.get(Calendar.DAY_OF_MONTH));
    }

    public static Calendar nextDayOfWeek(int dow) {
        Calendar date = Calendar.getInstance();
        int diff = dow - date.get(Calendar.DAY_OF_WEEK);
        if (diff <= 0) {
            diff += 7;
        }
        date.add(Calendar.DAY_OF_MONTH, diff);
        return date;
    }

    public static Calendar fromString(String date){
        Calendar cDate = Calendar.getInstance();
        try {
            date = correctFormat(date);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
            cDate.setTime(dateFormat.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cDate;
    }

    public static Integer getAge(String date) {
        Calendar calendar = Calendar.getInstance();
        date = correctFormat(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        if (date != null)
            try {
                calendar.setTime(dateFormat.parse(date));
                return Calendar.getInstance().get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        return 0;
    }

    private static int getMinutes(Date date) {
        Calendar oneC = Calendar.getInstance();
        if (date != null)
            oneC.setTime(date);
        return oneC.get(Calendar.MINUTE);
    }

    public static String toString(Date date) {
        Calendar dateCal = toCalendar(date);
        if (date == null)
            date = dateCal.getTime();
        Calendar now = Calendar.getInstance();
        if (dateCal.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR))
            return today + " " + new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date);
        else if (dateCal.get(Calendar.DAY_OF_YEAR) == (now.get(Calendar.DAY_OF_YEAR) - 1))
            return yesterday + " " + new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date);
        return new SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault()).format(date);
    }

    private static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null)
            cal.setTime(date);
        return cal;
    }

    public static String parse(String dateTime) {
        if (dateTime != null) {
            String[] dateTimeArray = dateTime.split("\\s+");
            if (dateTimeArray.length == 2)
                return toString(parse(dateTimeArray[0], dateTimeArray[1]));
            else return dateTime;
        }
        return "not information";
    }

    public static Date parse(String dates, String times) {
        Date date;
        try {
            date = new SimpleDateFormat(dateTimeFormat, Locale.getDefault()).parse(dates + " " + times);
        } catch (ParseException parse) {
            date = Calendar.getInstance().getTime();
        }
        return date;
    }



    public static String reformating(String ddmmyyyy) {
        if (ddmmyyyy != null) {
            if (!ddmmyyyy.contains("."))
                ddmmyyyy = ddmmyyyy.replaceAll("-", ".");
            String[] start = ddmmyyyy.split("\\.");
            if (start.length < 3)
                return "";
            if (start[0].length() > 2)
                return ddmmyyyy;
            start[1] = (start[1].length() == 1 ? "0" : "") + start[1];
            if (start.length == 3)
                return start[2] + "-" + start[1] + "-" + start[0];
            return "";
        } else return "";
    }

    public static String correctFormat(String date) {
        if (date == null)
            return "";
        if (date.contains("-"))
            date = date.replaceAll("-", ".");
        String[] start = date.split("\\.");
        if (start[0].length() > 2)
            return date;
        else return reformating(date);
    }

    public static Date fromIso(String iso) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        df.setTimeZone(tz);
        try {
            return df.parse(iso);
        } catch (ParseException e) {
            e.printStackTrace();
            return Calendar.getInstance().getTime();
        }
    }

    static String toAge(Date date) {
        if (date == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(now.get(Calendar.YEAR) - calendar.get(Calendar.YEAR));
    }

    public static int compareDDMMYYYY(String ddmmyyyy0, String ddmmyyyy1) {
        return reformating(ddmmyyyy0).compareTo(reformating(ddmmyyyy1));
    }
}
