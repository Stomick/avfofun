package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class LoginView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.LoginView> implements evfor.fun.skvader.mvp.views.LoginView {

	@Override
	public  void errorLogin() {
		ErrorLoginCommand errorLoginCommand = new ErrorLoginCommand();
		mViewCommands.beforeApply(errorLoginCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.LoginView view : mViews) {
			view.errorLogin();
		}

		mViewCommands.afterApply(errorLoginCommand);
	}

	@Override
	public  void gotoRegister() {
		GotoRegisterCommand gotoRegisterCommand = new GotoRegisterCommand();
		mViewCommands.beforeApply(gotoRegisterCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.LoginView view : mViews) {
			view.gotoRegister();
		}

		mViewCommands.afterApply(gotoRegisterCommand);
	}

	@Override
	public  void onComplete() {
		OnCompleteCommand onCompleteCommand = new OnCompleteCommand();
		mViewCommands.beforeApply(onCompleteCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.LoginView view : mViews) {
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

		for(evfor.fun.skvader.mvp.views.LoginView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class ErrorLoginCommand extends ViewCommand<evfor.fun.skvader.mvp.views.LoginView> {
		ErrorLoginCommand() {
			super("errorLogin", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.LoginView mvpView) {
			mvpView.errorLogin();
		}
	}

	public class GotoRegisterCommand extends ViewCommand<evfor.fun.skvader.mvp.views.LoginView> {
		GotoRegisterCommand() {
			super("gotoRegister", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.LoginView mvpView) {
			mvpView.gotoRegister();
		}
	}

	public class OnCompleteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.LoginView> {
		OnCompleteCommand() {
			super("onComplete", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.LoginView mvpView) {
			mvpView.onComplete();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.LoginView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.LoginView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
