package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class PartsView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.PartsView> implements evfor.fun.skvader.mvp.views.PartsView {

	@Override
	public  void addUser( evfor.fun.skvader.ui.models.UiUser user) {
		AddUserCommand addUserCommand = new AddUserCommand(user);
		mViewCommands.beforeApply(addUserCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.PartsView view : mViews) {
			view.addUser(user);
		}

		mViewCommands.afterApply(addUserCommand);
	}

	@Override
	public  void showRequestCount( int count) {
		ShowRequestCountCommand showRequestCountCommand = new ShowRequestCountCommand(count);
		mViewCommands.beforeApply(showRequestCountCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.PartsView view : mViews) {
			view.showRequestCount(count);
		}

		mViewCommands.afterApply(showRequestCountCommand);
	}

	@Override
	public  void showDeleteDialog( java.lang.String name,  java.lang.String eventCommName,  java.lang.String id) {
		ShowDeleteDialogCommand showDeleteDialogCommand = new ShowDeleteDialogCommand(name, eventCommName, id);
		mViewCommands.beforeApply(showDeleteDialogCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.PartsView view : mViews) {
			view.showDeleteDialog(name, eventCommName, id);
		}

		mViewCommands.afterApply(showDeleteDialogCommand);
	}

	@Override
	public  void removed( evfor.fun.skvader.ui.models.UiUser id) {
		RemovedCommand removedCommand = new RemovedCommand(id);
		mViewCommands.beforeApply(removedCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.PartsView view : mViews) {
			view.removed(id);
		}

		mViewCommands.afterApply(removedCommand);
	}

	@Override
	public  void onComplete() {
		OnCompleteCommand onCompleteCommand = new OnCompleteCommand();
		mViewCommands.beforeApply(onCompleteCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.PartsView view : mViews) {
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

		for(evfor.fun.skvader.mvp.views.PartsView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class AddUserCommand extends ViewCommand<evfor.fun.skvader.mvp.views.PartsView> {
		public final evfor.fun.skvader.ui.models.UiUser user;

		AddUserCommand( evfor.fun.skvader.ui.models.UiUser user) {
			super("addUser", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.user = user;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.PartsView mvpView) {
			mvpView.addUser(user);
		}
	}

	public class ShowRequestCountCommand extends ViewCommand<evfor.fun.skvader.mvp.views.PartsView> {
		public final int count;

		ShowRequestCountCommand( int count) {
			super("showRequestCount", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.count = count;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.PartsView mvpView) {
			mvpView.showRequestCount(count);
		}
	}

	public class ShowDeleteDialogCommand extends ViewCommand<evfor.fun.skvader.mvp.views.PartsView> {
		public final java.lang.String name;
		public final java.lang.String eventCommName;
		public final java.lang.String id;

		ShowDeleteDialogCommand( java.lang.String name,  java.lang.String eventCommName,  java.lang.String id) {
			super("showDeleteDialog", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.name = name;
			this.eventCommName = eventCommName;
			this.id = id;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.PartsView mvpView) {
			mvpView.showDeleteDialog(name, eventCommName, id);
		}
	}

	public class RemovedCommand extends ViewCommand<evfor.fun.skvader.mvp.views.PartsView> {
		public final evfor.fun.skvader.ui.models.UiUser id;

		RemovedCommand( evfor.fun.skvader.ui.models.UiUser id) {
			super("removed", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.id = id;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.PartsView mvpView) {
			mvpView.removed(id);
		}
	}

	public class OnCompleteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.PartsView> {
		OnCompleteCommand() {
			super("onComplete", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.PartsView mvpView) {
			mvpView.onComplete();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.PartsView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.PartsView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
