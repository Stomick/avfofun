package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class SelectLocationView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.SelectLocationView> implements evfor.fun.skvader.mvp.views.SelectLocationView {

	@Override
	public  void setCurrentLoc( evfor.fun.skvader.models.Location loc) {
		SetCurrentLocCommand setCurrentLocCommand = new SetCurrentLocCommand(loc);
		mViewCommands.beforeApply(setCurrentLocCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.SelectLocationView view : mViews) {
			view.setCurrentLoc(loc);
		}

		mViewCommands.afterApply(setCurrentLocCommand);
	}

	@Override
	public  void showInfo(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
		ShowInfoCommand showInfoCommand = new ShowInfoCommand(messageId, params);
		mViewCommands.beforeApply(showInfoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.SelectLocationView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class SetCurrentLocCommand extends ViewCommand<evfor.fun.skvader.mvp.views.SelectLocationView> {
		public final evfor.fun.skvader.models.Location loc;

		SetCurrentLocCommand( evfor.fun.skvader.models.Location loc) {
			super("setCurrentLoc", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.loc = loc;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.SelectLocationView mvpView) {
			mvpView.setCurrentLoc(loc);
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.SelectLocationView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.SelectLocationView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
