package com.team.noty.event.ui.models;

import android.support.annotation.DrawableRes;

import com.team.noty.event.utils.callbacks.CallBack;

public class UiMenuItem {
    public String text;
    @DrawableRes
    public int drawable;
    public CallBack action;

    public UiMenuItem(String text, int drawable) {
        this.text = text;
        this.drawable = drawable;
    }

    public UiMenuItem() {
    }

    public UiMenuItem(String text, int drawable, CallBack action) {
        this.text = text;
        this.drawable = drawable;
        this.action = action;
    }

    public UiMenuItem(String text, CallBack action) {
        this.text = text;
        this.action = action;
    }
}
