package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class EditCategoriesView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.EditCategoriesView> implements evfor.fun.skvader.mvp.views.EditCategoriesView {

	@Override
	public  void loadAllCategories( java.util.List<evfor.fun.skvader.models.Category> categories) {
		LoadAllCategoriesCommand loadAllCategoriesCommand = new LoadAllCategoriesCommand(categories);
		mViewCommands.beforeApply(loadAllCategoriesCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EditCategoriesView view : mViews) {
			view.loadAllCategories(categories);
		}

		mViewCommands.afterApply(loadAllCategoriesCommand);
	}

	@Override
	public  void checkCategory( java.lang.String id) {
		CheckCategoryCommand checkCategoryCommand = new CheckCategoryCommand(id);
		mViewCommands.beforeApply(checkCategoryCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EditCategoriesView view : mViews) {
			view.checkCategory(id);
		}

		mViewCommands.afterApply(checkCategoryCommand);
	}

	@Override
	public  void onComplete() {
		OnCompleteCommand onCompleteCommand = new OnCompleteCommand();
		mViewCommands.beforeApply(onCompleteCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EditCategoriesView view : mViews) {
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

		for(evfor.fun.skvader.mvp.views.EditCategoriesView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class LoadAllCategoriesCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EditCategoriesView> {
		public final java.util.List<evfor.fun.skvader.models.Category> categories;

		LoadAllCategoriesCommand( java.util.List<evfor.fun.skvader.models.Category> categories) {
			super("loadAllCategories", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.categories = categories;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EditCategoriesView mvpView) {
			mvpView.loadAllCategories(categories);
		}
	}

	public class CheckCategoryCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EditCategoriesView> {
		public final java.lang.String id;

		CheckCategoryCommand( java.lang.String id) {
			super("checkCategory", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.id = id;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EditCategoriesView mvpView) {
			mvpView.checkCategory(id);
		}
	}

	public class OnCompleteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EditCategoriesView> {
		OnCompleteCommand() {
			super("onComplete", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EditCategoriesView mvpView) {
			mvpView.onComplete();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EditCategoriesView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EditCategoriesView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
