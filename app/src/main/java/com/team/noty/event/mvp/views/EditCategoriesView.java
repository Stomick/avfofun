package com.team.noty.event.mvp.views;

import com.team.noty.event.models.Category;

import java.util.List;

public interface EditCategoriesView extends CompletableView {
    void loadAllCategories(List<Category> categories);
    void checkCategory(String id);
}
