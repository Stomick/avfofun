package com.team.noty.event.ui.utils.listeners;

import android.content.DialogInterface;

import com.team.noty.event.utils.callbacks.CallBack;

public class SimpleDismissListener implements DialogInterface.OnDismissListener {
    private CallBack callBack;

    public SimpleDismissListener(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        callBack.call();
    }
}
