package com.team.noty.event.ui.dialogs;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Toast;

public class Toaster {
    private Toast toast;
    private CountDownTimer toastCountDown;
    private int ms;

    public static Toaster duration(Context context, String text, int ms) {
        Toaster toaster = new Toaster();
        toaster.toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toaster.toast.show();
        toaster.ms = ms;
        toaster.start();
        return toaster;
    }

    private Toaster() {
    }

    public void cancel() {
        toastCountDown.cancel();
        toast.cancel();
    }

    private void start() {
        toastCountDown = new CountDownTimer(ms, 2000) {
            public void onTick(long millisUntilFinished) {
                toast.show();
            }

            public void onFinish() {
                toast.cancel();
            }
        };
        toastCountDown.start();
    }
}
