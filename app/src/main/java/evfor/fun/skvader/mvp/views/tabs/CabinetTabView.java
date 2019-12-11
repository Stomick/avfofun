package evfor.fun.skvader.mvp.views.tabs;

import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.Comment;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.mvp.views.BaseView;
import evfor.fun.skvader.ui.models.UiSoc;

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
