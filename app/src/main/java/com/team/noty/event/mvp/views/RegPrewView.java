package com.team.noty.event.mvp.views;

import com.team.noty.event.models.Category;

import java.util.List;

public interface RegPrewView extends CompletableView {
    void showInterests(List<Category> categories);
    void openPhoto();
    void setCity(String cityName);
}
