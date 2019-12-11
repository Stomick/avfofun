package com.team.noty.event.ui.utils;

import android.os.Handler;
import android.os.Message;

import com.team.noty.event.utils.callbacks.CallBack1;

public class Handle<T>{

    private T arg;
    private CallBack1<T> call;

    public static <T> void send(T arg, int ms, CallBack1<T> callBack){
        new Handle<>(arg, callBack).send(ms);
    }

    private Handle(T arg, CallBack1<T> call) {
        this.arg = arg;
        this.call = call;
    }
    public void send(int delay_ms){
        new Handler(this::exe).sendMessageDelayed(new Message(), delay_ms);
    }

    private boolean exe(Message ignore){
        call.call(arg);
        return true;
    }
}
