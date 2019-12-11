package com.team.noty.event.models;


import com.team.noty.event.repository.Identified;

public enum DistributionParticipant implements Identified {
    dismissed, confirmed;
    private String id;
    private ActId actId;

    public DistributionParticipant setId(ActId actId, String id) {
        this.id = id;
        this.actId = actId;
        return this;
    }

    public ActId getActId() {
        return actId;
    }

    @Override
    public String id() {
        return id;
    }
}
