package evfor.fun.skvader.models;

import java.util.Calendar;
import java.util.List;

public class Community extends Act {
    private static final String weekDays = "1234567";
    public List<String> days;
    public String bTime;
    public String eTime;
    public String forDisplayDays;

    public Community(String id) {
        super(id);
        isEvent = false;
    }

    public Community() {
        isEvent = false;
    }

    @Override
    public String displayDate() {
        return forDisplayDays + ", " + bTime + "-" + eTime;
    }

    @Override
    public boolean isPassed() {
        return false;
    }

    @Override
    protected String nextDate() {
        Calendar date = nextDayOfWeek(weekDays.indexOf(days.get(0)));
        for (String d : days) {
            Calendar current = nextDayOfWeek(weekDays.indexOf(d));
            if (date.compareTo(current) > 0)
                date = current;
        }
        return String.valueOf(date.get(Calendar.YEAR)) + "."
                + String.valueOf(date.get(Calendar.MONTH) + 1) + "."
                + String.valueOf(date.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    protected String beginTime() {
        return bTime;
    }

    private Calendar nextDayOfWeek(int dow) {
        Calendar date = Calendar.getInstance();
        int diff = dow - date.get(Calendar.DAY_OF_WEEK);
        if (diff <= 0) {
            diff += 7;
        }
        date.add(Calendar.DAY_OF_MONTH, diff);
        return date;
    }
}
