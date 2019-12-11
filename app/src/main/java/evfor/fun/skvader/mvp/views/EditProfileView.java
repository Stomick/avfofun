package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.User;

public interface EditProfileView extends CompletableView {
    void exit();
    void loadProfile(User uiUser);
}
