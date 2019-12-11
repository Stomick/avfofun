package evfor.fun.skvader.mvp.presenters;

import com.arellomobile.mvp.ViewStateProvider;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.MvpViewState;

public class ActListPresenter$$ViewStateProvider extends ViewStateProvider {
	
	@Override
	public MvpViewState<? extends MvpView> getViewState() {
		return new evfor.fun.skvader.mvp.views.EventCommListView$$State();
	}
}