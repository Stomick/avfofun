package com.team.noty.event.mvp.views;

import com.team.noty.event.models.User;

public interface EditProfileView extends CompletableView {
    void exit();
    void loadProfile(User uiUser);
}
