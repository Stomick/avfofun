package com.team.noty.event.mvp.views;

import com.team.noty.event.models.ActId;
import com.team.noty.event.models.Message;
import com.team.noty.event.models.User;

import java.util.List;

public interface MessageView extends BaseView {
    void loadMessage(List<Message> messages);
    void setTitle(String eventName);
    void loadUser(User user);
    void newMessage(Message message);
    void updateMessage(Message message);
    void record(int time);
    void play(int time, int maxTime);
    void loadUsers(List<User> users);
    void write(String id);
    void openUser(String id);
    void openUsers(ActId actId, boolean c);
    void connected();
    void disconnected();
    void permissionDenied();
    void notCanWrite();
    void error();
}
