package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class NotificationView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.NotificationView> implements evfor.fun.skvader.mvp.views.NotificationView {

	@Override
	public  void showList( java.util.List<evfor.fun.skvader.models.Notification> notifications) {
		ShowListCommand showListCommand = new ShowListCommand(notifications);
		mViewCommands.beforeApply(showListCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.NotificationView view : mViews) {
			view.showList(notifications);
		}

		mViewCommands.afterApply(showListCommand);
	}

	@Override
	public  void addNotification( evfor.fun.skvader.models.Notification notification) {
		AddNotificationCommand addNotificationCommand = new AddNotificationCommand(notification);
		mViewCommands.beforeApply(addNotificationCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.NotificationView view : mViews) {
			view.addNotification(notification);
		}

		mViewCommands.afterApply(addNotificationCommand);
	}

	@Override
	public  void showInfo(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
		ShowInfoCommand showInfoCommand = new ShowInfoCommand(messageId, params);
		mViewCommands.beforeApply(showInfoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.NotificationView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class ShowListCommand extends ViewCommand<evfor.fun.skvader.mvp.views.NotificationView> {
		public final java.util.List<evfor.fun.skvader.models.Notification> notifications;

		ShowListCommand( java.util.List<evfor.fun.skvader.models.Notification> notifications) {
			super("showList", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.notifications = notifications;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.NotificationView mvpView) {
			mvpView.showList(notifications);
		}
	}

	public class AddNotificationCommand extends ViewCommand<evfor.fun.skvader.mvp.views.NotificationView> {
		public final evfor.fun.skvader.models.Notification notification;

		AddNotificationCommand( evfor.fun.skvader.models.Notification notification) {
			super("addNotification", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.notification = notification;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.NotificationView mvpView) {
			mvpView.addNotification(notification);
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.NotificationView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.NotificationView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
