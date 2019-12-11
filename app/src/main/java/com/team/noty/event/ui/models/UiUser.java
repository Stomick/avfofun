package com.team.noty.event.ui.models;

import android.support.annotation.NonNull;

import com.team.noty.event.models.User;
import com.team.noty.event.repository.Identified;
import com.team.noty.event.utils.Predicatable;

public class UiUser implements Predicatable, Comparable<UiUser>, Identified {

    public final User user;

    public UiUser(User user) {
        this.user = user;
    }

    public UiUser(User user, boolean canWrite) {
        this.user = user;
        this.canWrite = canWrite;
    }

    public boolean isCheck = false;
    public boolean canWrite = false;

    @Override
    public boolean contain(String word) {
        return user.firstname.toLowerCase().contains(word.toLowerCase());
    }

    @Override
    public int compareTo(@NonNull UiUser uiUser) {
        return user.compareTo(uiUser.user);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UiUser)
            return ((UiUser) obj).user.equals(user);
        return super.equals(obj);
    }

    @Override
    public String id() {
        return user.id();
    }
}
