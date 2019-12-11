package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.FilterModel;

import java.util.List;

public interface SearchView extends BaseView {
    void showList(List<Act> events);
    void openFilter(FilterModel model);
}
