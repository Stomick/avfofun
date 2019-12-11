package com.team.noty.event.network.models.request.create;

public class RqCreateComm extends RqEventCom {

    public String visiting_days;
    public String start_time ;
    public String end_time ;

    @Override
    public String path() {
        return "community";
    }
}
