package evfor.fun.skvader.exceptions;

import evfor.fun.skvader.models.ActId;

public class EventCommBlockedException extends Exception {
    public ActId actId;

    public EventCommBlockedException(ActId actId) {
        this.actId = actId;
    }
}
