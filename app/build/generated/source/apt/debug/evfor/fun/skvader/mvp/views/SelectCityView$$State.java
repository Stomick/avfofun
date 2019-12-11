package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class SelectCityView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.SelectCityView> implements evfor.fun.skvader.mvp.views.SelectCityView {

	@Override
	public  void showList( java.util.List<evfor.fun.skvader.models.City> cities) {
		ShowListCommand showListCommand = new ShowListCommand(cities);
		mViewCommands.beforeApply(showListCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.SelectCityView view : mViews) {
			view.showList(cities);
		}

		mViewCommands.afterApply(showListCommand);
	}

	@Override
	public  void showInfo(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
		ShowInfoCommand showInfoCommand = new ShowInfoCommand(messageId, params);
		mViewCommands.beforeApply(showInfoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.SelectCityView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class ShowListCommand extends ViewCommand<evfor.fun.skvader.mvp.views.SelectCityView> {
		public final java.util.List<evfor.fun.skvader.models.City> cities;

		ShowListCommand( java.util.List<evfor.fun.skvader.models.City> cities) {
			super("showList", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.cities = cities;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.SelectCityView mvpView) {
			mvpView.showList(cities);
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.SelectCityView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.SelectCityView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
