package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class IntroView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.IntroView> implements evfor.fun.skvader.mvp.views.IntroView {

	@Override
	public  void openMain() {
		OpenMainCommand openMainCommand = new OpenMainCommand();
		mViewCommands.beforeApply(openMainCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.IntroView view : mViews) {
			view.openMain();
		}

		mViewCommands.afterApply(openMainCommand);
	}

	@Override
	public  void needAuth() {
		NeedAuthCommand needAuthCommand = new NeedAuthCommand();
		mViewCommands.beforeApply(needAuthCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.IntroView view : mViews) {
			view.needAuth();
		}

		mViewCommands.afterApply(needAuthCommand);
	}

	@Override
	public  void openReg() {
		OpenRegCommand openRegCommand = new OpenRegCommand();
		mViewCommands.beforeApply(openRegCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.IntroView view : mViews) {
			view.openReg();
		}

		mViewCommands.afterApply(openRegCommand);
	}

	@Override
	public  void showInfo(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
		ShowInfoCommand showInfoCommand = new ShowInfoCommand(messageId, params);
		mViewCommands.beforeApply(showInfoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.IntroView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class OpenMainCommand extends ViewCommand<evfor.fun.skvader.mvp.views.IntroView> {
		OpenMainCommand() {
			super("openMain", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.IntroView mvpView) {
			mvpView.openMain();
		}
	}

	public class NeedAuthCommand extends ViewCommand<evfor.fun.skvader.mvp.views.IntroView> {
		NeedAuthCommand() {
			super("needAuth", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.IntroView mvpView) {
			mvpView.needAuth();
		}
	}

	public class OpenRegCommand extends ViewCommand<evfor.fun.skvader.mvp.views.IntroView> {
		OpenRegCommand() {
			super("openReg", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.IntroView mvpView) {
			mvpView.openReg();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.IntroView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.IntroView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
