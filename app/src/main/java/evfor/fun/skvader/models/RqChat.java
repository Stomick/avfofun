package evfor.fun.skvader.models;

import android.support.annotation.Nullable;

import evfor.fun.skvader.repository.Identified;

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
