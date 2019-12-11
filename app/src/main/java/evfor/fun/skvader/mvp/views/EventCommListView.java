package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.Act;

import java.util.List;

public interface EventCommListView extends CompletableView {
    void loadList(List<Act> event);
    void setCounts(int partC, int creatorC);
}
