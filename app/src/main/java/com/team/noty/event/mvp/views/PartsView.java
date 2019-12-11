package com.team.noty.event.mvp.views;

import com.team.noty.event.ui.models.UiUser;

import java.util.List;

public interface PartsView extends CompletableView {
    void addUser(UiUser user);
    void showRequestCount(int count);
    void showDeleteDialog(String name,String eventCommName, String id);
    void removed(UiUser id);
}
