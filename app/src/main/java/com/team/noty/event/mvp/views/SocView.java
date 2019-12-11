package com.team.noty.event.mvp.views;

import com.team.noty.event.ui.models.UiSoc;

import java.util.List;

public interface SocView extends CompletableView {
    void loadList(List<UiSoc> list);
}

