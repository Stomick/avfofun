package evfor.fun.skvader.mvp.views.tabs;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class CabinetTabView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.tabs.CabinetTabView> implements evfor.fun.skvader.mvp.views.tabs.CabinetTabView {

	@Override
	public  void showUser( evfor.fun.skvader.models.User user) {
		ShowUserCommand showUserCommand = new ShowUserCommand(user);
		mViewCommands.beforeApply(showUserCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.CabinetTabView view : mViews) {
			view.showUser(user);
		}

		mViewCommands.afterApply(showUserCommand);
	}

	@Override
	public  void showCommunities( java.util.List<evfor.fun.skvader.models.Act> events) {
		ShowCommunitiesCommand showCommunitiesCommand = new ShowCommunitiesCommand(events);
		mViewCommands.beforeApply(showCommunitiesCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.CabinetTabView view : mViews) {
			view.showCommunities(events);
		}

		mViewCommands.afterApply(showCommunitiesCommand);
	}

	@Override
	public  void showEvents( java.util.List<evfor.fun.skvader.models.Act> events) {
		ShowEventsCommand showEventsCommand = new ShowEventsCommand(events);
		mViewCommands.beforeApply(showEventsCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.CabinetTabView view : mViews) {
			view.showEvents(events);
		}

		mViewCommands.afterApply(showEventsCommand);
	}

	@Override
	public  void showComments( java.util.List<evfor.fun.skvader.models.Comment> comments) {
		ShowCommentsCommand showCommentsCommand = new ShowCommentsCommand(comments);
		mViewCommands.beforeApply(showCommentsCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.CabinetTabView view : mViews) {
			view.showComments(comments);
		}

		mViewCommands.afterApply(showCommentsCommand);
	}

	@Override
	public  void openPhoto( java.lang.String url) {
		OpenPhotoCommand openPhotoCommand = new OpenPhotoCommand(url);
		mViewCommands.beforeApply(openPhotoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.CabinetTabView view : mViews) {
			view.openPhoto(url);
		}

		mViewCommands.afterApply(openPhotoCommand);
	}

	@Override
	public  void openDialog( java.lang.String user_id) {
		OpenDialogCommand openDialogCommand = new OpenDialogCommand(user_id);
		mViewCommands.beforeApply(openDialogCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.CabinetTabView view : mViews) {
			view.openDialog(user_id);
		}

		mViewCommands.afterApply(openDialogCommand);
	}

	@Override
	public  void loadSoc( java.util.List<evfor.fun.skvader.ui.models.UiSoc> socs) {
		LoadSocCommand loadSocCommand = new LoadSocCommand(socs);
		mViewCommands.beforeApply(loadSocCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.CabinetTabView view : mViews) {
			view.loadSoc(socs);
		}

		mViewCommands.afterApply(loadSocCommand);
	}

	@Override
	public  void showSendButton() {
		ShowSendButtonCommand showSendButtonCommand = new ShowSendButtonCommand();
		mViewCommands.beforeApply(showSendButtonCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.CabinetTabView view : mViews) {
			view.showSendButton();
		}

		mViewCommands.afterApply(showSendButtonCommand);
	}

	@Override
	public  void changePhoto( java.lang.String s) {
		ChangePhotoCommand changePhotoCommand = new ChangePhotoCommand(s);
		mViewCommands.beforeApply(changePhotoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.CabinetTabView view : mViews) {
			view.changePhoto(s);
		}

		mViewCommands.afterApply(changePhotoCommand);
	}

	@Override
	public  void showInfo(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
		ShowInfoCommand showInfoCommand = new ShowInfoCommand(messageId, params);
		mViewCommands.beforeApply(showInfoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.tabs.CabinetTabView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class ShowUserCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.CabinetTabView> {
		public final evfor.fun.skvader.models.User user;

		ShowUserCommand( evfor.fun.skvader.models.User user) {
			super("showUser", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.user = user;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.CabinetTabView mvpView) {
			mvpView.showUser(user);
		}
	}

	public class ShowCommunitiesCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.CabinetTabView> {
		public final java.util.List<evfor.fun.skvader.models.Act> events;

		ShowCommunitiesCommand( java.util.List<evfor.fun.skvader.models.Act> events) {
			super("showCommunities", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.events = events;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.CabinetTabView mvpView) {
			mvpView.showCommunities(events);
		}
	}

	public class ShowEventsCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.CabinetTabView> {
		public final java.util.List<evfor.fun.skvader.models.Act> events;

		ShowEventsCommand( java.util.List<evfor.fun.skvader.models.Act> events) {
			super("showEvents", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.events = events;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.CabinetTabView mvpView) {
			mvpView.showEvents(events);
		}
	}

	public class ShowCommentsCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.CabinetTabView> {
		public final java.util.List<evfor.fun.skvader.models.Comment> comments;

		ShowCommentsCommand( java.util.List<evfor.fun.skvader.models.Comment> comments) {
			super("showComments", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.comments = comments;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.CabinetTabView mvpView) {
			mvpView.showComments(comments);
		}
	}

	public class OpenPhotoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.CabinetTabView> {
		public final java.lang.String url;

		OpenPhotoCommand( java.lang.String url) {
			super("openPhoto", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.url = url;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.CabinetTabView mvpView) {
			mvpView.openPhoto(url);
		}
	}

	public class OpenDialogCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.CabinetTabView> {
		public final java.lang.String user_id;

		OpenDialogCommand( java.lang.String user_id) {
			super("openDialog", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.user_id = user_id;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.CabinetTabView mvpView) {
			mvpView.openDialog(user_id);
		}
	}

	public class LoadSocCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.CabinetTabView> {
		public final java.util.List<evfor.fun.skvader.ui.models.UiSoc> socs;

		LoadSocCommand( java.util.List<evfor.fun.skvader.ui.models.UiSoc> socs) {
			super("loadSoc", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.socs = socs;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.CabinetTabView mvpView) {
			mvpView.loadSoc(socs);
		}
	}

	public class ShowSendButtonCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.CabinetTabView> {
		ShowSendButtonCommand() {
			super("showSendButton", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.CabinetTabView mvpView) {
			mvpView.showSendButton();
		}
	}

	public class ChangePhotoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.CabinetTabView> {
		public final java.lang.String s;

		ChangePhotoCommand( java.lang.String s) {
			super("changePhoto", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.s = s;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.CabinetTabView mvpView) {
			mvpView.changePhoto(s);
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.tabs.CabinetTabView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.tabs.CabinetTabView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
