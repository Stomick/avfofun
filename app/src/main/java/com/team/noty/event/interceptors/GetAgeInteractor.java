package com.team.noty.event.interceptors;

import java.util.Calendar;

import javax.inject.Inject;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class GetAgeInteractor implements Interactor<Integer, Calendar> {
    private Calendar now;

    @Inject
    GetAgeInteractor() {
        now = Calendar.getInstance();
    }

    @Override
    public Integer call(Calendar calendar) {
        int diff = now.get(YEAR) - calendar.get(YEAR);
        if (birthdayIsOver(calendar))
            diff--;
        return diff;
    }

    private boolean birthdayIsOver(Calendar calendar) {
        return calendar.get(MONTH) > now.get(MONTH) ||
                (calendar.get(MONTH) == now.get(MONTH) && calendar.get(DATE) > now.get(DATE));
    }
}
