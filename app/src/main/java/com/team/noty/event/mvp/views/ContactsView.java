package com.team.noty.event.mvp.views;

import com.team.noty.event.ui.models.UiContact;

import java.util.List;

public interface ContactsView extends BaseView {
    void showContacts(List<UiContact> contactList);
}
