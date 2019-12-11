package com.team.noty.event.mvp.views.tabs;

import com.team.noty.event.models.Act;
import com.team.noty.event.models.Comment;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.views.BaseView;
import com.team.noty.event.ui.models.UiSoc;

import java.util.List;

public interface CabinetTabView extends BaseView {
    void showUser(User user);
    void showCommunities(List<Act> events);
    void showEvents(List<Act> events);
    void showComments(List<Comment> comments);
    void openPhoto(String url);
    void openDialog(String user_id);
    void loadSoc(List<UiSoc> socs);
    void showSendButton();
    void changePhoto(String s);
}
