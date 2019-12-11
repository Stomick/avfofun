package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.Category;

import java.util.List;

public interface RegPrewView extends CompletableView {
    void showInterests(List<Category> categories);
    void openPhoto();
    void setCity(String cityName);
}
