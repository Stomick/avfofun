package com.team.noty.event.utils;

public class EventBus {
    public static void post(String o){
        org.greenrobot.eventbus.EventBus.getDefault().post(o);
    }

    public static void register(Object o){
        org.greenrobot.eventbus.EventBus.getDefault().register(o);
    }

    public static void unregister(Object o){
        org.greenrobot.eventbus.EventBus.getDefault().unregister(o);
    }
    public static void removeAll(Object o)
    {
        org.greenrobot.eventbus.EventBus.getDefault().removeAllStickyEvents();
    }
}
