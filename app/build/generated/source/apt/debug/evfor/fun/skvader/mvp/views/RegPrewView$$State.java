package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class RegPrewView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.RegPrewView> implements evfor.fun.skvader.mvp.views.RegPrewView {

	@Override
	public  void showInterests( java.util.List<evfor.fun.skvader.models.Category> categories) {
		ShowInterestsCommand showInterestsCommand = new ShowInterestsCommand(categories);
		mViewCommands.beforeApply(showInterestsCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.RegPrewView view : mViews) {
			view.showInterests(categories);
		}

		mViewCommands.afterApply(showInterestsCommand);
	}

	@Override
	public  void openPhoto() {
		OpenPhotoCommand openPhotoCommand = new OpenPhotoCommand();
		mViewCommands.beforeApply(openPhotoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.RegPrewView view : mViews) {
			view.openPhoto();
		}

		mViewCommands.afterApply(openPhotoCommand);
	}

	@Override
	public  void setCity( java.lang.String cityName) {
		SetCityCommand setCityCommand = new SetCityCommand(cityName);
		mViewCommands.beforeApply(setCityCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.RegPrewView view : mViews) {
			view.setCity(cityName);
		}

		mViewCommands.afterApply(setCityCommand);
	}

	@Override
	public  void onComplete() {
		OnCompleteCommand onCompleteCommand = new OnCompleteCommand();
		mViewCommands.beforeApply(onCompleteCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.RegPrewView view : mViews) {
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

		for(evfor.fun.skvader.mvp.views.RegPrewView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class ShowInterestsCommand extends ViewCommand<evfor.fun.skvader.mvp.views.RegPrewView> {
		public final java.util.List<evfor.fun.skvader.models.Category> categories;

		ShowInterestsCommand( java.util.List<evfor.fun.skvader.models.Category> categories) {
			super("showInterests", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.categories = categories;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.RegPrewView mvpView) {
			mvpView.showInterests(categories);
		}
	}

	public class OpenPhotoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.RegPrewView> {
		OpenPhotoCommand() {
			super("openPhoto", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.RegPrewView mvpView) {
			mvpView.openPhoto();
		}
	}

	public class SetCityCommand extends ViewCommand<evfor.fun.skvader.mvp.views.RegPrewView> {
		public final java.lang.String cityName;

		SetCityCommand( java.lang.String cityName) {
			super("setCity", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.cityName = cityName;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.RegPrewView mvpView) {
			mvpView.setCity(cityName);
		}
	}

	public class OnCompleteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.RegPrewView> {
		OnCompleteCommand() {
			super("onComplete", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.RegPrewView mvpView) {
			mvpView.onComplete();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.RegPrewView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.RegPrewView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
