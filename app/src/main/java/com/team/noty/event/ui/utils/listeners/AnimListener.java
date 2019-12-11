package com.team.noty.event.ui.utils.listeners;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

import com.team.noty.event.utils.callbacks.CallBack;

public class AnimListener implements AnimatorListener {

    private CallBack end, start, cancel, repeat;

    private AnimListener(){}

    public static AnimListener withEndOrCancel(CallBack endOrCancel){
        AnimListener animListener = new AnimListener();
        animListener.end = endOrCancel;
        animListener.cancel = endOrCancel;
        return animListener;
    }
    public static AnimListener withEnd(CallBack end){
        AnimListener animListener = new AnimListener();
        animListener.end = end;
        return animListener;
    }

    @Override
    public void onAnimationStart(Animator animator) {
        if(start!=null)
            start.call();
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        if(end!=null)
            end.call();
    }

    @Override
    public void onAnimationCancel(Animator animator) {
        if(cancel!=null)
            cancel.call();
    }

    @Override
    public void onAnimationRepeat(Animator animator) {
        if(repeat!=null)
            repeat.call();
    }
}
