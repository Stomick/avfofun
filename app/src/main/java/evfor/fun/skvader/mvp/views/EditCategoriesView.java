package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.Category;

import java.util.List;

public interface EditCategoriesView extends CompletableView {
    void loadAllCategories(List<Category> categories);
    void checkCategory(String id);
}
