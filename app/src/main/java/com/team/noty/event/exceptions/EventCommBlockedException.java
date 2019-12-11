package com.team.noty.event.exceptions;

import com.team.noty.event.models.ActId;

public class EventCommBlockedException extends Exception {
    public ActId actId;

    public EventCommBlockedException(ActId actId) {
        this.actId = actId;
    }
}
