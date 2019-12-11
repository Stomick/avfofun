package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class EventCommListView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.EventCommListView> implements evfor.fun.skvader.mvp.views.EventCommListView {

	@Override
	public  void loadList( java.util.List<evfor.fun.skvader.models.Act> event) {
		LoadListCommand loadListCommand = new LoadListCommand(event);
		mViewCommands.beforeApply(loadListCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventCommListView view : mViews) {
			view.loadList(event);
		}

		mViewCommands.afterApply(loadListCommand);
	}

	@Override
	public  void setCounts( int partC,  int creatorC) {
		SetCountsCommand setCountsCommand = new SetCountsCommand(partC, creatorC);
		mViewCommands.beforeApply(setCountsCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventCommListView view : mViews) {
			view.setCounts(partC, creatorC);
		}

		mViewCommands.afterApply(setCountsCommand);
	}

	@Override
	public  void onComplete() {
		OnCompleteCommand onCompleteCommand = new OnCompleteCommand();
		mViewCommands.beforeApply(onCompleteCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventCommListView view : mViews) {
			view.onComplete();
		}

		mViewCommands.afterApply(onCompleteCommand);
	}

	@Override
	public  void showInfo(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
		ShowInfoCommand showInfoCommand = new ShowInfoCommand(messageId, params);
		mViewCommands.beforeApply(showInfoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventCommListView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class LoadListCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventCommListView> {
		public final java.util.List<evfor.fun.skvader.models.Act> event;

		LoadListCommand( java.util.List<evfor.fun.skvader.models.Act> event) {
			super("loadList", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.event = event;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventCommListView mvpView) {
			mvpView.loadList(event);
		}
	}

	public class SetCountsCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventCommListView> {
		public final int partC;
		public final int creatorC;

		SetCountsCommand( int partC,  int creatorC) {
			super("setCounts", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.partC = partC;
			this.creatorC = creatorC;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventCommListView mvpView) {
			mvpView.setCounts(partC, creatorC);
		}
	}

	public class OnCompleteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventCommListView> {
		OnCompleteCommand() {
			super("onComplete", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventCommListView mvpView) {
			mvpView.onComplete();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventCommListView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventCommListView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
