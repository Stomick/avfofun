package com.team.noty.event.mvp.views;

import com.team.noty.event.models.ActId;
import com.team.noty.event.models.User;
import com.team.noty.event.ui.models.UiAct;
import com.team.noty.event.ui.models.UiFullEvent;
import com.team.noty.event.ui.models.UiUser;

import java.util.List;

public interface EventView extends BaseView {
    void showAct(UiAct act);
    void showCreator(UiUser user);
    void showParts(List<User> users);
    void openDialog(String user_id);
    void openProfile(String user_id);
    void openEdit(ActId actId);
    void openChat(ActId actId);
    void openComplaint(ActId actId);
    void openDelete(ActId actId);
    void ageException();
    void showMessageButtonCreator();
    void hideUserList();
    void copyLink(String link);
    void shareLink(String link);
    void close(String error);
    void dismissGoBtn();
    void confirmGoBtn();
    void falseGoBtn();
    void requestGoBtn();
    void passedGoBtn(boolean imJoin);
    void noPlacesGoBtn();
    void openFeedBack(String id);
    void blocked();
}
