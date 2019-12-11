package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.User;

public interface GiveFeedbackView extends CompletableView {
    void loadUser(User user, int age);
    void loadEvent(String image, String title);
    void showError(String message);
}
