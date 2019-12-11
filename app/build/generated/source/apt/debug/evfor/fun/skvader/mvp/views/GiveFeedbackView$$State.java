package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class GiveFeedbackView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.GiveFeedbackView> implements evfor.fun.skvader.mvp.views.GiveFeedbackView {

	@Override
	public  void loadUser( evfor.fun.skvader.models.User user,  int age) {
		LoadUserCommand loadUserCommand = new LoadUserCommand(user, age);
		mViewCommands.beforeApply(loadUserCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.GiveFeedbackView view : mViews) {
			view.loadUser(user, age);
		}

		mViewCommands.afterApply(loadUserCommand);
	}

	@Override
	public  void loadEvent( java.lang.String image,  java.lang.String title) {
		LoadEventCommand loadEventCommand = new LoadEventCommand(image, title);
		mViewCommands.beforeApply(loadEventCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.GiveFeedbackView view : mViews) {
			view.loadEvent(image, title);
		}

		mViewCommands.afterApply(loadEventCommand);
	}

	@Override
	public  void showError( java.lang.String message) {
		ShowErrorCommand showErrorCommand = new ShowErrorCommand(message);
		mViewCommands.beforeApply(showErrorCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.GiveFeedbackView view : mViews) {
			view.showError(message);
		}

		mViewCommands.afterApply(showErrorCommand);
	}

	@Override
	public  void onComplete() {
		OnCompleteCommand onCompleteCommand = new OnCompleteCommand();
		mViewCommands.beforeApply(onCompleteCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.GiveFeedbackView view : mViews) {
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

		for(evfor.fun.skvader.mvp.views.GiveFeedbackView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class LoadUserCommand extends ViewCommand<evfor.fun.skvader.mvp.views.GiveFeedbackView> {
		public final evfor.fun.skvader.models.User user;
		public final int age;

		LoadUserCommand( evfor.fun.skvader.models.User user,  int age) {
			super("loadUser", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.user = user;
			this.age = age;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.GiveFeedbackView mvpView) {
			mvpView.loadUser(user, age);
		}
	}

	public class LoadEventCommand extends ViewCommand<evfor.fun.skvader.mvp.views.GiveFeedbackView> {
		public final java.lang.String image;
		public final java.lang.String title;

		LoadEventCommand( java.lang.String image,  java.lang.String title) {
			super("loadEvent", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.image = image;
			this.title = title;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.GiveFeedbackView mvpView) {
			mvpView.loadEvent(image, title);
		}
	}

	public class ShowErrorCommand extends ViewCommand<evfor.fun.skvader.mvp.views.GiveFeedbackView> {
		public final java.lang.String message;

		ShowErrorCommand( java.lang.String message) {
			super("showError", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.message = message;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.GiveFeedbackView mvpView) {
			mvpView.showError(message);
		}
	}

	public class OnCompleteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.GiveFeedbackView> {
		OnCompleteCommand() {
			super("onComplete", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.GiveFeedbackView mvpView) {
			mvpView.onComplete();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.GiveFeedbackView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.GiveFeedbackView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
