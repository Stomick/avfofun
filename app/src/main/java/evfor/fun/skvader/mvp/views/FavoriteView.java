package evfor.fun.skvader.mvp.views;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import evfor.fun.skvader.models.Act;

import java.util.List;

public interface FavoriteView extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showList(List<Act> events);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void setCounts(int events, int comm);
}
