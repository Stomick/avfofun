package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class FilterView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.FilterView> implements evfor.fun.skvader.mvp.views.FilterView {

	@Override
	public  void setCity( java.lang.String city) {
		SetCityCommand setCityCommand = new SetCityCommand(city);
		mViewCommands.beforeApply(setCityCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.FilterView view : mViews) {
			view.setCity(city);
		}

		mViewCommands.afterApply(setCityCommand);
	}

	@Override
	public  void finish( evfor.fun.skvader.models.FilterModel filterModel) {
		FinishCommand finishCommand = new FinishCommand(filterModel);
		mViewCommands.beforeApply(finishCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.FilterView view : mViews) {
			view.finish(filterModel);
		}

		mViewCommands.afterApply(finishCommand);
	}

	@Override
	public  void showInfo(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
		ShowInfoCommand showInfoCommand = new ShowInfoCommand(messageId, params);
		mViewCommands.beforeApply(showInfoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.FilterView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class SetCityCommand extends ViewCommand<evfor.fun.skvader.mvp.views.FilterView> {
		public final java.lang.String city;

		SetCityCommand( java.lang.String city) {
			super("setCity", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.city = city;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.FilterView mvpView) {
			mvpView.setCity(city);
		}
	}

	public class FinishCommand extends ViewCommand<evfor.fun.skvader.mvp.views.FilterView> {
		public final evfor.fun.skvader.models.FilterModel filterModel;

		FinishCommand( evfor.fun.skvader.models.FilterModel filterModel) {
			super("finish", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.filterModel = filterModel;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.FilterView mvpView) {
			mvpView.finish(filterModel);
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.FilterView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.FilterView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
