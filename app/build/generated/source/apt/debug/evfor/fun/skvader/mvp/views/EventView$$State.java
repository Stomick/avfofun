package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class EventView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.EventView> implements evfor.fun.skvader.mvp.views.EventView {

	@Override
	public  void showAct( evfor.fun.skvader.ui.models.UiAct act) {
		ShowActCommand showActCommand = new ShowActCommand(act);
		mViewCommands.beforeApply(showActCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.showAct(act);
		}

		mViewCommands.afterApply(showActCommand);
	}

	@Override
	public  void showCreator( evfor.fun.skvader.ui.models.UiUser user) {
		ShowCreatorCommand showCreatorCommand = new ShowCreatorCommand(user);
		mViewCommands.beforeApply(showCreatorCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.showCreator(user);
		}

		mViewCommands.afterApply(showCreatorCommand);
	}

	@Override
	public  void showParts( java.util.List<evfor.fun.skvader.models.User> users) {
		ShowPartsCommand showPartsCommand = new ShowPartsCommand(users);
		mViewCommands.beforeApply(showPartsCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.showParts(users);
		}

		mViewCommands.afterApply(showPartsCommand);
	}

	@Override
	public  void openDialog( java.lang.String user_id) {
		OpenDialogCommand openDialogCommand = new OpenDialogCommand(user_id);
		mViewCommands.beforeApply(openDialogCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.openDialog(user_id);
		}

		mViewCommands.afterApply(openDialogCommand);
	}

	@Override
	public  void openProfile( java.lang.String user_id) {
		OpenProfileCommand openProfileCommand = new OpenProfileCommand(user_id);
		mViewCommands.beforeApply(openProfileCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.openProfile(user_id);
		}

		mViewCommands.afterApply(openProfileCommand);
	}

	@Override
	public  void openEdit( evfor.fun.skvader.models.ActId actId) {
		OpenEditCommand openEditCommand = new OpenEditCommand(actId);
		mViewCommands.beforeApply(openEditCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.openEdit(actId);
		}

		mViewCommands.afterApply(openEditCommand);
	}

	@Override
	public  void openChat( evfor.fun.skvader.models.ActId actId) {
		OpenChatCommand openChatCommand = new OpenChatCommand(actId);
		mViewCommands.beforeApply(openChatCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.openChat(actId);
		}

		mViewCommands.afterApply(openChatCommand);
	}

	@Override
	public  void openComplaint( evfor.fun.skvader.models.ActId actId) {
		OpenComplaintCommand openComplaintCommand = new OpenComplaintCommand(actId);
		mViewCommands.beforeApply(openComplaintCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.openComplaint(actId);
		}

		mViewCommands.afterApply(openComplaintCommand);
	}

	@Override
	public  void openDelete( evfor.fun.skvader.models.ActId actId) {
		OpenDeleteCommand openDeleteCommand = new OpenDeleteCommand(actId);
		mViewCommands.beforeApply(openDeleteCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.openDelete(actId);
		}

		mViewCommands.afterApply(openDeleteCommand);
	}

	@Override
	public  void ageException() {
		AgeExceptionCommand ageExceptionCommand = new AgeExceptionCommand();
		mViewCommands.beforeApply(ageExceptionCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.ageException();
		}

		mViewCommands.afterApply(ageExceptionCommand);
	}

	@Override
	public  void showMessageButtonCreator() {
		ShowMessageButtonCreatorCommand showMessageButtonCreatorCommand = new ShowMessageButtonCreatorCommand();
		mViewCommands.beforeApply(showMessageButtonCreatorCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.showMessageButtonCreator();
		}

		mViewCommands.afterApply(showMessageButtonCreatorCommand);
	}

	@Override
	public  void hideUserList() {
		HideUserListCommand hideUserListCommand = new HideUserListCommand();
		mViewCommands.beforeApply(hideUserListCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.hideUserList();
		}

		mViewCommands.afterApply(hideUserListCommand);
	}

	@Override
	public  void copyLink( java.lang.String link) {
		CopyLinkCommand copyLinkCommand = new CopyLinkCommand(link);
		mViewCommands.beforeApply(copyLinkCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.copyLink(link);
		}

		mViewCommands.afterApply(copyLinkCommand);
	}

	@Override
	public  void shareLink( java.lang.String link) {
		ShareLinkCommand shareLinkCommand = new ShareLinkCommand(link);
		mViewCommands.beforeApply(shareLinkCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.shareLink(link);
		}

		mViewCommands.afterApply(shareLinkCommand);
	}

	@Override
	public  void close( java.lang.String error) {
		CloseCommand closeCommand = new CloseCommand(error);
		mViewCommands.beforeApply(closeCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.close(error);
		}

		mViewCommands.afterApply(closeCommand);
	}

	@Override
	public  void dismissGoBtn() {
		DismissGoBtnCommand dismissGoBtnCommand = new DismissGoBtnCommand();
		mViewCommands.beforeApply(dismissGoBtnCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.dismissGoBtn();
		}

		mViewCommands.afterApply(dismissGoBtnCommand);
	}

	@Override
	public  void confirmGoBtn() {
		ConfirmGoBtnCommand confirmGoBtnCommand = new ConfirmGoBtnCommand();
		mViewCommands.beforeApply(confirmGoBtnCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.confirmGoBtn();
		}

		mViewCommands.afterApply(confirmGoBtnCommand);
	}

	@Override
	public  void falseGoBtn() {
		FalseGoBtnCommand falseGoBtnCommand = new FalseGoBtnCommand();
		mViewCommands.beforeApply(falseGoBtnCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.falseGoBtn();
		}

		mViewCommands.afterApply(falseGoBtnCommand);
	}

	@Override
	public  void requestGoBtn() {
		RequestGoBtnCommand requestGoBtnCommand = new RequestGoBtnCommand();
		mViewCommands.beforeApply(requestGoBtnCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.requestGoBtn();
		}

		mViewCommands.afterApply(requestGoBtnCommand);
	}

	@Override
	public  void passedGoBtn( boolean imJoin) {
		PassedGoBtnCommand passedGoBtnCommand = new PassedGoBtnCommand(imJoin);
		mViewCommands.beforeApply(passedGoBtnCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.passedGoBtn(imJoin);
		}

		mViewCommands.afterApply(passedGoBtnCommand);
	}

	@Override
	public  void noPlacesGoBtn() {
		NoPlacesGoBtnCommand noPlacesGoBtnCommand = new NoPlacesGoBtnCommand();
		mViewCommands.beforeApply(noPlacesGoBtnCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.noPlacesGoBtn();
		}

		mViewCommands.afterApply(noPlacesGoBtnCommand);
	}

	@Override
	public  void openFeedBack( java.lang.String id) {
		OpenFeedBackCommand openFeedBackCommand = new OpenFeedBackCommand(id);
		mViewCommands.beforeApply(openFeedBackCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.openFeedBack(id);
		}

		mViewCommands.afterApply(openFeedBackCommand);
	}

	@Override
	public  void blocked() {
		BlockedCommand blockedCommand = new BlockedCommand();
		mViewCommands.beforeApply(blockedCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.blocked();
		}

		mViewCommands.afterApply(blockedCommand);
	}

	@Override
	public  void showInfo(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
		ShowInfoCommand showInfoCommand = new ShowInfoCommand(messageId, params);
		mViewCommands.beforeApply(showInfoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.EventView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class ShowActCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final evfor.fun.skvader.ui.models.UiAct act;

		ShowActCommand( evfor.fun.skvader.ui.models.UiAct act) {
			super("showAct", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.act = act;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.showAct(act);
		}
	}

	public class ShowCreatorCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final evfor.fun.skvader.ui.models.UiUser user;

		ShowCreatorCommand( evfor.fun.skvader.ui.models.UiUser user) {
			super("showCreator", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.user = user;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.showCreator(user);
		}
	}

	public class ShowPartsCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final java.util.List<evfor.fun.skvader.models.User> users;

		ShowPartsCommand( java.util.List<evfor.fun.skvader.models.User> users) {
			super("showParts", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.users = users;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.showParts(users);
		}
	}

	public class OpenDialogCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final java.lang.String user_id;

		OpenDialogCommand( java.lang.String user_id) {
			super("openDialog", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.user_id = user_id;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.openDialog(user_id);
		}
	}

	public class OpenProfileCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final java.lang.String user_id;

		OpenProfileCommand( java.lang.String user_id) {
			super("openProfile", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.user_id = user_id;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.openProfile(user_id);
		}
	}

	public class OpenEditCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final evfor.fun.skvader.models.ActId actId;

		OpenEditCommand( evfor.fun.skvader.models.ActId actId) {
			super("openEdit", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.actId = actId;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.openEdit(actId);
		}
	}

	public class OpenChatCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final evfor.fun.skvader.models.ActId actId;

		OpenChatCommand( evfor.fun.skvader.models.ActId actId) {
			super("openChat", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.actId = actId;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.openChat(actId);
		}
	}

	public class OpenComplaintCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final evfor.fun.skvader.models.ActId actId;

		OpenComplaintCommand( evfor.fun.skvader.models.ActId actId) {
			super("openComplaint", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.actId = actId;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.openComplaint(actId);
		}
	}

	public class OpenDeleteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final evfor.fun.skvader.models.ActId actId;

		OpenDeleteCommand( evfor.fun.skvader.models.ActId actId) {
			super("openDelete", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.actId = actId;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.openDelete(actId);
		}
	}

	public class AgeExceptionCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		AgeExceptionCommand() {
			super("ageException", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.ageException();
		}
	}

	public class ShowMessageButtonCreatorCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		ShowMessageButtonCreatorCommand() {
			super("showMessageButtonCreator", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.showMessageButtonCreator();
		}
	}

	public class HideUserListCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		HideUserListCommand() {
			super("hideUserList", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.hideUserList();
		}
	}

	public class CopyLinkCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final java.lang.String link;

		CopyLinkCommand( java.lang.String link) {
			super("copyLink", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.link = link;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.copyLink(link);
		}
	}

	public class ShareLinkCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final java.lang.String link;

		ShareLinkCommand( java.lang.String link) {
			super("shareLink", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.link = link;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.shareLink(link);
		}
	}

	public class CloseCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final java.lang.String error;

		CloseCommand( java.lang.String error) {
			super("close", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.error = error;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.close(error);
		}
	}

	public class DismissGoBtnCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		DismissGoBtnCommand() {
			super("dismissGoBtn", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.dismissGoBtn();
		}
	}

	public class ConfirmGoBtnCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		ConfirmGoBtnCommand() {
			super("confirmGoBtn", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.confirmGoBtn();
		}
	}

	public class FalseGoBtnCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		FalseGoBtnCommand() {
			super("falseGoBtn", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.falseGoBtn();
		}
	}

	public class RequestGoBtnCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		RequestGoBtnCommand() {
			super("requestGoBtn", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.requestGoBtn();
		}
	}

	public class PassedGoBtnCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final boolean imJoin;

		PassedGoBtnCommand( boolean imJoin) {
			super("passedGoBtn", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.imJoin = imJoin;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.passedGoBtn(imJoin);
		}
	}

	public class NoPlacesGoBtnCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		NoPlacesGoBtnCommand() {
			super("noPlacesGoBtn", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.noPlacesGoBtn();
		}
	}

	public class OpenFeedBackCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final java.lang.String id;

		OpenFeedBackCommand( java.lang.String id) {
			super("openFeedBack", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.id = id;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.openFeedBack(id);
		}
	}

	public class BlockedCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		BlockedCommand() {
			super("blocked", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.blocked();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.EventView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.EventView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
