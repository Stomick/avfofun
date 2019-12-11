package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.ui.models.UiUser;

public interface PartsView extends CompletableView {
    void addUser(UiUser user);
    void showRequestCount(int count);
    void showDeleteDialog(String name,String eventCommName, String id);
    void removed(UiUser id);
}
