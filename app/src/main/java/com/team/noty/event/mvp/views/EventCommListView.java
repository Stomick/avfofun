package com.team.noty.event.mvp.views;

import com.team.noty.event.models.Act;

import java.util.List;

public interface EventCommListView extends CompletableView {
    void loadList(List<Act> event);
    void setCounts(int partC, int creatorC);
}
