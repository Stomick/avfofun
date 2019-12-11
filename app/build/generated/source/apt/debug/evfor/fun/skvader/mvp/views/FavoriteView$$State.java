package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class FavoriteView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.FavoriteView> implements evfor.fun.skvader.mvp.views.FavoriteView {

	@Override
	public  void showList( java.util.List<evfor.fun.skvader.models.Act> events) {
		ShowListCommand showListCommand = new ShowListCommand(events);
		mViewCommands.beforeApply(showListCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.FavoriteView view : mViews) {
			view.showList(events);
		}

		mViewCommands.afterApply(showListCommand);
	}

	@Override
	public  void setCounts( int events,  int comm) {
		SetCountsCommand setCountsCommand = new SetCountsCommand(events, comm);
		mViewCommands.beforeApply(setCountsCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.FavoriteView view : mViews) {
			view.setCounts(events, comm);
		}

		mViewCommands.afterApply(setCountsCommand);
	}

	@Override
	public  void showInfo(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
		ShowInfoCommand showInfoCommand = new ShowInfoCommand(messageId, params);
		mViewCommands.beforeApply(showInfoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.FavoriteView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class ShowListCommand extends ViewCommand<evfor.fun.skvader.mvp.views.FavoriteView> {
		public final java.util.List<evfor.fun.skvader.models.Act> events;

		ShowListCommand( java.util.List<evfor.fun.skvader.models.Act> events) {
			super("showList", com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy.class);
			this.events = events;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.FavoriteView mvpView) {
			mvpView.showList(events);
		}
	}

	public class SetCountsCommand extends ViewCommand<evfor.fun.skvader.mvp.views.FavoriteView> {
		public final int events;
		public final int comm;

		SetCountsCommand( int events,  int comm) {
			super("setCounts", com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy.class);
			this.events = events;
			this.comm = comm;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.FavoriteView mvpView) {
			mvpView.setCounts(events, comm);
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.FavoriteView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.FavoriteView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
