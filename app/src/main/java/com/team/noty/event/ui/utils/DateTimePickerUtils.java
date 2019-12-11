package com.team.noty.event.ui.utils;

import android.os.Build;
import android.text.format.DateUtils;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.team.noty.event.R;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class

DateTimePickerUtils {
    public static String dateToString(DatePicker picker) {

        return picker.getContext().getString(
                R.string.date_template,
                setFill0(String.valueOf(picker.getDayOfMonth()), 2),
                setFill0(String.valueOf(picker.getMonth() + 1), 2),
                String.valueOf(picker.getYear()));
    }

    public static String timeToString(TimePicker picker) {
        String H = String.valueOf(getH(picker)), M = String.valueOf(getM(picker));
            return picker.getContext().getString(
                    R.string.time_template, setFill0(H, 2), setFill0(M, 2));
    }

    public static String setFill0(String s, int c) {
        StringBuilder sBuilder = new StringBuilder(s);
        for(int i = 0; i < c - sBuilder.length(); i++)
            sBuilder.insert(0, "0");
        return sBuilder.toString();
    }

    private static Integer getH(TimePicker picker) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return picker.getHour();
        } else return picker.getCurrentHour();
    }

    private static Integer getM(TimePicker picker) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return picker.getMinute();
        } else return picker.getCurrentMinute();
    }

    public static Date getDate(DatePicker picker) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(picker.getYear(), picker.getMonth(), picker.getDayOfMonth());
        return calendar.getTime();
    }
}
