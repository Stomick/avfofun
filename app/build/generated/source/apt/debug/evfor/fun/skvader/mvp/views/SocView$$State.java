package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class SocView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.SocView> implements evfor.fun.skvader.mvp.views.SocView {

	@Override
	public  void loadList( java.util.List<evfor.fun.skvader.ui.models.UiSoc> list) {
		LoadListCommand loadListCommand = new LoadListCommand(list);
		mViewCommands.beforeApply(loadListCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.SocView view : mViews) {
			view.loadList(list);
		}

		mViewCommands.afterApply(loadListCommand);
	}

	@Override
	public  void onComplete() {
		OnCompleteCommand onCompleteCommand = new OnCompleteCommand();
		mViewCommands.beforeApply(onCompleteCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.SocView view : mViews) {
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

		for(evfor.fun.skvader.mvp.views.SocView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class LoadListCommand extends ViewCommand<evfor.fun.skvader.mvp.views.SocView> {
		public final java.util.List<evfor.fun.skvader.ui.models.UiSoc> list;

		LoadListCommand( java.util.List<evfor.fun.skvader.ui.models.UiSoc> list) {
			super("loadList", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.list = list;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.SocView mvpView) {
			mvpView.loadList(list);
		}
	}

	public class OnCompleteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.SocView> {
		OnCompleteCommand() {
			super("onComplete", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.SocView mvpView) {
			mvpView.onComplete();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.SocView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.SocView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
