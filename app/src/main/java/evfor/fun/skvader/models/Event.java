package evfor.fun.skvader.models;

import evfor.fun.skvader.utils.DateFormatter;

import java.util.Calendar;

public class Event extends Act {
    public String date;
    public String time;

    public Event(String id) {
        super(id);
        isEvent = true;
    }

    public Event() {
        isEvent = true;
    }

    @Override
    public String displayDate() {
        return date + ", " + time;
    }

    @Override
    public boolean isPassed() {
        return date != null && DateFormatter.parse(date, time).compareTo(Calendar.getInstance().getTime()) < 0;
    }

    @Override
    protected String nextDate() {
        return date;
    }

    @Override
    protected String beginTime() {
        return time;
    }
}
