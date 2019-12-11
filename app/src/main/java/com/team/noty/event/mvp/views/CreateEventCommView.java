package com.team.noty.event.mvp.views;

import com.team.noty.event.models.Act;
import com.team.noty.event.models.Category;
import com.team.noty.event.ui.models.UiCategory;

import java.util.List;

public interface CreateEventCommView extends CompletableView {
    void setCategories(List<Category> categories);
    void loadEventComm(Act eventComm);
    void completeEdit();
}
