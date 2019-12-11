package evfor.fun.skvader.mvp.views.tabs;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class SearchTabView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.tabs.SearchTabView> implements evfor.fun.skvader.mvp.views.tabs.SearchTabView {

	@Override
	public  void showCategory( java.util.List<evfor.fun.skvader.models.Category> interestItems) {
		ShowCategoryCommand showCategoryCommand = new ShowCategoryCommand(interestItems);
		mViewCommands.beforeApply(showCategoryCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.SearchTabView view : mViews) {
			view.showCategory(interestItems);
		}

		mViewCommands.afterApply(showCategoryCommand);
	}

	@Override
	public  void showEvents( java.util.List<evfor.fun.skvader.models.Act> events) {
		ShowEventsCommand showEventsCommand = new ShowEventsCommand(events);
		mViewCommands.beforeApply(showEventsCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.SearchTabView view : mViews) {
			view.showEvents(events);
		}

		mViewCommands.afterApply(showEventsCommand);
	}

	@Override
	public  void openEvents( java.lang.String categoryId) {
		OpenEventsCommand openEventsCommand = new OpenEventsCommand(categoryId);
		mViewCommands.beforeApply(openEventsCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.SearchTabView view : mViews) {
			view.openEvents(categoryId);
		}

		mViewCommands.afterApply(openEventsCommand);
	}

	@Override
	public  void showInfo(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
		ShowInfoCommand showInfoCommand = new ShowInfoCommand(messageId, params);
		mViewCommands.beforeApply(showInfoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.SearchTabView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class ShowCategoryCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.SearchTabView> {
		public final java.util.List<evfor.fun.skvader.models.Category> interestItems;

		ShowCategoryCommand( java.util.List<evfor.fun.skvader.models.Category> interestItems) {
			super("showCategory", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.interestItems = interestItems;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.SearchTabView mvpView) {
			mvpView.showCategory(interestItems);
		}
	}

	public class ShowEventsCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.SearchTabView> {
		public final java.util.List<evfor.fun.skvader.models.Act> events;

		ShowEventsCommand( java.util.List<evfor.fun.skvader.models.Act> events) {
			super("showEvents", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.events = events;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.SearchTabView mvpView) {
			mvpView.showEvents(events);
		}
	}

	public class OpenEventsCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.SearchTabView> {
		public final java.lang.String categoryId;

		OpenEventsCommand( java.lang.String categoryId) {
			super("openEvents", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.categoryId = categoryId;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.SearchTabView mvpView) {
			mvpView.openEvents(categoryId);
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.SearchTabView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.SearchTabView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
