package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.ui.models.UiSoc;

import java.util.List;

public interface SocView extends CompletableView {
    void loadList(List<UiSoc> list);
}

