package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class EditProfileView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.EditProfileView> implements evfor.fun.skvader.mvp.views.EditProfileView {

	@Override
	public  void exit() {
		ExitCommand exitCommand = new ExitCommand();
		mViewCommands.beforeApply(exitCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EditProfileView view : mViews) {
			view.exit();
		}

		mViewCommands.afterApply(exitCommand);
	}

	@Override
	public  void loadProfile( evfor.fun.skvader.models.User uiUser) {
		LoadProfileCommand loadProfileCommand = new LoadProfileCommand(uiUser);
		mViewCommands.beforeApply(loadProfileCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EditProfileView view : mViews) {
			view.loadProfile(uiUser);
		}

		mViewCommands.afterApply(loadProfileCommand);
	}

	@Override
	public  void onComplete() {
		OnCompleteCommand onCompleteCommand = new OnCompleteCommand();
		mViewCommands.beforeApply(onCompleteCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EditProfileView view : mViews) {
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

		for(evfor.fun.skvader.mvp.views.EditProfileView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class ExitCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EditProfileView> {
		ExitCommand() {
			super("exit", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EditProfileView mvpView) {
			mvpView.exit();
		}
	}

	public class LoadProfileCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EditProfileView> {
		public final evfor.fun.skvader.models.User uiUser;

		LoadProfileCommand( evfor.fun.skvader.models.User uiUser) {
			super("loadProfile", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.uiUser = uiUser;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EditProfileView mvpView) {
			mvpView.loadProfile(uiUser);
		}
	}

	public class OnCompleteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EditProfileView> {
		OnCompleteCommand() {
			super("onComplete", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EditProfileView mvpView) {
			mvpView.onComplete();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EditProfileView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EditProfileView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
