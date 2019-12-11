package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.Category;

import java.util.List;

public interface CreateEventCommView extends CompletableView {
    void setCategories(List<Category> categories);
    void loadEventComm(Act eventComm);
    void completeEdit();
}
