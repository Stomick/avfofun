package com.team.noty.event.mvp.views.tabs;

import com.team.noty.event.models.Act;
import com.team.noty.event.models.Category;
import com.team.noty.event.mvp.views.BaseView;
import com.team.noty.event.ui.models.UiCategory;
import com.team.noty.event.ui.models.UiPopularEvent;

import java.util.List;

public interface SearchTabView extends BaseView {
    void showCategory(List<Category> interestItems);
    void showEvents(List<Act> events);
    void openEvents(String categoryId);
}
