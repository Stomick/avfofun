package com.team.noty.event.models;

public enum ActUpdate {
    ENTER("in"), LEAVE("out");

    public ActId actId;
    private String doName;

    ActUpdate(String doName) {
        this.doName = doName;
    }

    public String getDoName() {
        return doName;
    }

    public ActUpdate setAct(ActId actId) {
        this.actId = actId;
        return this;
    }
}
