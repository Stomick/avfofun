package com.team.noty.event.mvp.views;

import com.team.noty.event.models.User;

public interface GiveFeedbackView extends CompletableView {
    void loadUser(User user, int age);
    void loadEvent(String image, String title);
    void showError(String message);
}
