package com.team.noty.event.ui.utils.listeners;

import android.content.DialogInterface;

import com.team.noty.event.utils.callbacks.CallBack;

public class SimpleDialogMultiListener implements DialogInterface.OnClickListener {

    private CallBack[] callBacks;

    public SimpleDialogMultiListener(CallBack... callBacks) {
        this.callBacks = callBacks;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        i = i < 0 ? 0 : i;
        if (i >= 0 && i < callBacks.length)
            callBacks[i].call();
        dialogInterface.dismiss();
    }
}
