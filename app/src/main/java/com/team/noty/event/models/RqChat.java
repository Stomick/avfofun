package com.team.noty.event.models;

import android.support.annotation.Nullable;

import com.team.noty.event.repository.Identified;

public class RqChat {
    public ActId actId;
    @Nullable
    public Identified userId;

    public RqChat(ActId actId) {
        this.actId = actId;
        this.userId = null;
    }

    public RqChat(ActId actId, @Nullable Identified userId) {
        this.actId = actId;
        this.userId = userId;
    }
}
