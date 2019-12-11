package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class SearchView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.SearchView> implements evfor.fun.skvader.mvp.views.SearchView {

	@Override
	public  void showList( java.util.List<evfor.fun.skvader.models.Act> events) {
		ShowListCommand showListCommand = new ShowListCommand(events);
		mViewCommands.beforeApply(showListCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.SearchView view : mViews) {
			view.showList(events);
		}

		mViewCommands.afterApply(showListCommand);
	}

	@Override
	public  void openFilter( evfor.fun.skvader.models.FilterModel model) {
		OpenFilterCommand openFilterCommand = new OpenFilterCommand(model);
		mViewCommands.beforeApply(openFilterCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.SearchView view : mViews) {
			view.openFilter(model);
		}

		mViewCommands.afterApply(openFilterCommand);
	}

	@Override
	public  void showInfo(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
		ShowInfoCommand showInfoCommand = new ShowInfoCommand(messageId, params);
		mViewCommands.beforeApply(showInfoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.SearchView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class ShowListCommand extends ViewCommand<evfor.fun.skvader.mvp.views.SearchView> {
		public final java.util.List<evfor.fun.skvader.models.Act> events;

		ShowListCommand( java.util.List<evfor.fun.skvader.models.Act> events) {
			super("showList", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.events = events;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.SearchView mvpView) {
			mvpView.showList(events);
		}
	}

	public class OpenFilterCommand extends ViewCommand<evfor.fun.skvader.mvp.views.SearchView> {
		public final evfor.fun.skvader.models.FilterModel model;

		OpenFilterCommand( evfor.fun.skvader.models.FilterModel model) {
			super("openFilter", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.model = model;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.SearchView mvpView) {
			mvpView.openFilter(model);
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.SearchView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.SearchView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
