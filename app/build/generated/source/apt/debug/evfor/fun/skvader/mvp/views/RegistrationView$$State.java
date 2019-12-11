package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class RegistrationView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.RegistrationView> implements evfor.fun.skvader.mvp.views.RegistrationView {

	@Override
	public  void nextStep() {
		NextStepCommand nextStepCommand = new NextStepCommand();
		mViewCommands.beforeApply(nextStepCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.RegistrationView view : mViews) {
			view.nextStep();
		}

		mViewCommands.afterApply(nextStepCommand);
	}

	@Override
	public  void error() {
		ErrorCommand errorCommand = new ErrorCommand();
		mViewCommands.beforeApply(errorCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.RegistrationView view : mViews) {
			view.error();
		}

		mViewCommands.afterApply(errorCommand);
	}

	@Override
	public  void onComplete() {
		OnCompleteCommand onCompleteCommand = new OnCompleteCommand();
		mViewCommands.beforeApply(onCompleteCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.RegistrationView view : mViews) {
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

		for(evfor.fun.skvader.mvp.views.RegistrationView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class NextStepCommand extends ViewCommand<evfor.fun.skvader.mvp.views.RegistrationView> {
		NextStepCommand() {
			super("nextStep", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.RegistrationView mvpView) {
			mvpView.nextStep();
		}
	}

	public class ErrorCommand extends ViewCommand<evfor.fun.skvader.mvp.views.RegistrationView> {
		ErrorCommand() {
			super("error", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.RegistrationView mvpView) {
			mvpView.error();
		}
	}

	public class OnCompleteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.RegistrationView> {
		OnCompleteCommand() {
			super("onComplete", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.RegistrationView mvpView) {
			mvpView.onComplete();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.RegistrationView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.RegistrationView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
