package com.team.noty.event.ui.utils.listeners;

import android.content.DialogInterface;
import android.app.AlertDialog;

import com.team.noty.event.utils.callbacks.CallBack1;

public class SimpleDialogRadioListeners implements DialogInterface.OnClickListener {

    private CallBack1<Integer> callBack;

    public SimpleDialogRadioListeners(CallBack1<Integer> callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(callBack!=null)
            callBack.call(((AlertDialog) dialogInterface).getListView().getCheckedItemPosition());
    }
}
