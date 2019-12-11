package com.team.noty.event.exceptions;

public class NotFoundException extends Exception {
    private String what;

    public NotFoundException(String what) {
        this.what = what;
    }

    public String getWhat() {
        return what;
    }
}
