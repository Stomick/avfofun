package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.ui.models.UiAct;
import evfor.fun.skvader.ui.models.UiUser;

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
