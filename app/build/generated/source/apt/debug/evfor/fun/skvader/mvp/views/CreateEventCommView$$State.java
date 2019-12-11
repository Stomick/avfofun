package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class CreateEventCommView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.CreateEventCommView> implements evfor.fun.skvader.mvp.views.CreateEventCommView {

	@Override
	public  void setCategories( java.util.List<evfor.fun.skvader.models.Category> categories) {
		SetCategoriesCommand setCategoriesCommand = new SetCategoriesCommand(categories);
		mViewCommands.beforeApply(setCategoriesCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.CreateEventCommView view : mViews) {
			view.setCategories(categories);
		}

		mViewCommands.afterApply(setCategoriesCommand);
	}

	@Override
	public  void loadEventComm( evfor.fun.skvader.models.Act eventComm) {
		LoadEventCommCommand loadEventCommCommand = new LoadEventCommCommand(eventComm);
		mViewCommands.beforeApply(loadEventCommCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.CreateEventCommView view : mViews) {
			view.loadEventComm(eventComm);
		}

		mViewCommands.afterApply(loadEventCommCommand);
	}

	@Override
	public  void completeEdit() {
		CompleteEditCommand completeEditCommand = new CompleteEditCommand();
		mViewCommands.beforeApply(completeEditCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.CreateEventCommView view : mViews) {
			view.completeEdit();
		}

		mViewCommands.afterApply(completeEditCommand);
	}

	@Override
	public  void onComplete() {
		OnCompleteCommand onCompleteCommand = new OnCompleteCommand();
		mViewCommands.beforeApply(onCompleteCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.CreateEventCommView view : mViews) {
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

		for(evfor.fun.skvader.mvp.views.CreateEventCommView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class SetCategoriesCommand extends ViewCommand<evfor.fun.skvader.mvp.views.CreateEventCommView> {
		public final java.util.List<evfor.fun.skvader.models.Category> categories;

		SetCategoriesCommand( java.util.List<evfor.fun.skvader.models.Category> categories) {
			super("setCategories", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.categories = categories;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.CreateEventCommView mvpView) {
			mvpView.setCategories(categories);
		}
	}

	public class LoadEventCommCommand extends ViewCommand<evfor.fun.skvader.mvp.views.CreateEventCommView> {
		public final evfor.fun.skvader.models.Act eventComm;

		LoadEventCommCommand( evfor.fun.skvader.models.Act eventComm) {
			super("loadEventComm", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.eventComm = eventComm;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.CreateEventCommView mvpView) {
			mvpView.loadEventComm(eventComm);
		}
	}

	public class CompleteEditCommand extends ViewCommand<evfor.fun.skvader.mvp.views.CreateEventCommView> {
		CompleteEditCommand() {
			super("completeEdit", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.CreateEventCommView mvpView) {
			mvpView.completeEdit();
		}
	}

	public class OnCompleteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.CreateEventCommView> {
		OnCompleteCommand() {
			super("onComplete", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.CreateEventCommView mvpView) {
			mvpView.onComplete();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.CreateEventCommView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.CreateEventCommView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
