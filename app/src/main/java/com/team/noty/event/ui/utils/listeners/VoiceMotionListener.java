package com.team.noty.event.ui.utils.listeners;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import com.team.noty.event.utils.callbacks.CallBack;

public class VoiceMotionListener implements View.OnTouchListener {

    private CallBack startVoice, endVoice, cancelVoice;
    private float startX;

    public VoiceMotionListener(CallBack startVoice, CallBack endVoice, CallBack cancelVoice) {
        this.startVoice = startVoice;
        this.endVoice = endVoice;
        this.cancelVoice = cancelVoice;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = motionEvent.getX();
                startVoice.call();
                break;
            case MotionEvent.ACTION_UP:
                float endX = motionEvent.getX();
                int w = Math.abs(view.getWidth());
                if (Math.abs(endX - startX) > w)
                    cancelVoice.call();
                else
                    endVoice.call();
                break;
        }
        return true;
    }
}
