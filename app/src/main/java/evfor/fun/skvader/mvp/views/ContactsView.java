package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.ui.models.UiContact;

import java.util.List;

public interface ContactsView extends BaseView {
    void showContacts(List<UiContact> contactList);
}
