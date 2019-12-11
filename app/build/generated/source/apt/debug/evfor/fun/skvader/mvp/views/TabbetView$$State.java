package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class TabbetView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.TabbetView> implements evfor.fun.skvader.mvp.views.TabbetView {

	@Override
	public  void setNotificationCount( java.lang.String count) {
		SetNotificationCountCommand setNotificationCountCommand = new SetNotificationCountCommand(count);
		mViewCommands.beforeApply(setNotificationCountCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.TabbetView view : mViews) {
			view.setNotificationCount(count);
		}

		mViewCommands.afterApply(setNotificationCountCommand);
	}

	@Override
	public  void onComplete() {
		OnCompleteCommand onCompleteCommand = new OnCompleteCommand();
		mViewCommands.beforeApply(onCompleteCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.TabbetView view : mViews) {
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

		for(evfor.fun.skvader.mvp.views.TabbetView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class SetNotificationCountCommand extends ViewCommand<evfor.fun.skvader.mvp.views.TabbetView> {
		public final java.lang.String count;

		SetNotificationCountCommand( java.lang.String count) {
			super("setNotificationCount", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.count = count;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.TabbetView mvpView) {
			mvpView.setNotificationCount(count);
		}
	}

	public class OnCompleteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.TabbetView> {
		OnCompleteCommand() {
			super("onComplete", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.TabbetView mvpView) {
			mvpView.onComplete();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.TabbetView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.TabbetView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
