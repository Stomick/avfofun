package evfor.fun.skvader.mvp.views.tabs;

import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.mvp.views.BaseView;

import java.util.List;

public interface SearchTabView extends BaseView {
    void showCategory(List<Category> interestItems);
    void showEvents(List<Act> events);
    void openEvents(String categoryId);
}
