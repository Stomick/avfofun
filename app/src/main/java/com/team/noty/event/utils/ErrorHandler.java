package com.team.noty.event.utils;

import android.content.res.Resources;

import com.team.noty.event.R;

import javax.inject.Inject;

import retrofit2.HttpException;

public class ErrorHandler implements iHandler<Throwable>{

    private ErrorDisplay errorDisplay;
    private Resources resources;

    @Inject
    public ErrorHandler(Resources resources) {
        this.resources = resources;
    }

    public void setErrorDisplay(ErrorDisplay errorDisplay) {
        this.errorDisplay = errorDisplay == null ? empty() : errorDisplay;
    }

    public void removeErrorDisplay() {
        errorDisplay = empty();
    }

    private ErrorDisplay empty() {
        return (t, m) -> {};
    }

    @Override
    public void handle(Throwable throwable) {
        errorDisplay.display(resources.getString(R.string.error), getMessage(throwable));
    }

    private String getMessage(Throwable t) {
        if (t instanceof HttpException)
            return ((HttpException) t).response().raw().request().url() + " code = " + String.valueOf(((HttpException) t).code());
        else if (t instanceof com.jakewharton.retrofit2.adapter.rxjava2.HttpException)
            return ((com.jakewharton.retrofit2.adapter.rxjava2.HttpException) t).response().raw().request().url() + " code = " + String.valueOf(((com.jakewharton.retrofit2.adapter.rxjava2.HttpException) t).code());
        if (t.getMessage() == null || t.getMessage().equals("null"))
            return t.getClass().getSimpleName();
        return t.getMessage();
    }

    public interface ErrorDisplay {
        void display(String title, String message);
    }
}
