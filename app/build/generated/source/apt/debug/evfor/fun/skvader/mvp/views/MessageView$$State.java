package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class MessageView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.MessageView> implements evfor.fun.skvader.mvp.views.MessageView {

	@Override
	public  void loadMessage( java.util.List<evfor.fun.skvader.models.Message> messages) {
		LoadMessageCommand loadMessageCommand = new LoadMessageCommand(messages);
		mViewCommands.beforeApply(loadMessageCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.loadMessage(messages);
		}

		mViewCommands.afterApply(loadMessageCommand);
	}

	@Override
	public  void setTitle( java.lang.String eventName) {
		SetTitleCommand setTitleCommand = new SetTitleCommand(eventName);
		mViewCommands.beforeApply(setTitleCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.setTitle(eventName);
		}

		mViewCommands.afterApply(setTitleCommand);
	}

	@Override
	public  void loadUser( evfor.fun.skvader.models.User user) {
		LoadUserCommand loadUserCommand = new LoadUserCommand(user);
		mViewCommands.beforeApply(loadUserCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.loadUser(user);
		}

		mViewCommands.afterApply(loadUserCommand);
	}

	@Override
	public  void newMessage( evfor.fun.skvader.models.Message message) {
		NewMessageCommand newMessageCommand = new NewMessageCommand(message);
		mViewCommands.beforeApply(newMessageCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.newMessage(message);
		}

		mViewCommands.afterApply(newMessageCommand);
	}

	@Override
	public  void updateMessage( evfor.fun.skvader.models.Message message) {
		UpdateMessageCommand updateMessageCommand = new UpdateMessageCommand(message);
		mViewCommands.beforeApply(updateMessageCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.updateMessage(message);
		}

		mViewCommands.afterApply(updateMessageCommand);
	}

	@Override
	public  void record( int time) {
		RecordCommand recordCommand = new RecordCommand(time);
		mViewCommands.beforeApply(recordCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.record(time);
		}

		mViewCommands.afterApply(recordCommand);
	}

	@Override
	public  void play( int time,  int maxTime) {
		PlayCommand playCommand = new PlayCommand(time, maxTime);
		mViewCommands.beforeApply(playCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.play(time, maxTime);
		}

		mViewCommands.afterApply(playCommand);
	}

	@Override
	public  void loadUsers( java.util.List<evfor.fun.skvader.models.User> users) {
		LoadUsersCommand loadUsersCommand = new LoadUsersCommand(users);
		mViewCommands.beforeApply(loadUsersCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.loadUsers(users);
		}

		mViewCommands.afterApply(loadUsersCommand);
	}

	@Override
	public  void write( java.lang.String id) {
		WriteCommand writeCommand = new WriteCommand(id);
		mViewCommands.beforeApply(writeCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.write(id);
		}

		mViewCommands.afterApply(writeCommand);
	}

	@Override
	public  void openUser( java.lang.String id) {
		OpenUserCommand openUserCommand = new OpenUserCommand(id);
		mViewCommands.beforeApply(openUserCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.openUser(id);
		}

		mViewCommands.afterApply(openUserCommand);
	}

	@Override
	public  void openUsers( evfor.fun.skvader.models.ActId actId,  boolean c) {
		OpenUsersCommand openUsersCommand = new OpenUsersCommand(actId, c);
		mViewCommands.beforeApply(openUsersCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.openUsers(actId, c);
		}

		mViewCommands.afterApply(openUsersCommand);
	}

	@Override
	public  void connected() {
		ConnectedCommand connectedCommand = new ConnectedCommand();
		mViewCommands.beforeApply(connectedCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.connected();
		}

		mViewCommands.afterApply(connectedCommand);
	}

	@Override
	public  void disconnected() {
		DisconnectedCommand disconnectedCommand = new DisconnectedCommand();
		mViewCommands.beforeApply(disconnectedCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.disconnected();
		}

		mViewCommands.afterApply(disconnectedCommand);
	}

	@Override
	public  void permissionDenied() {
		PermissionDeniedCommand permissionDeniedCommand = new PermissionDeniedCommand();
		mViewCommands.beforeApply(permissionDeniedCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.permissionDenied();
		}

		mViewCommands.afterApply(permissionDeniedCommand);
	}

	@Override
	public  void notCanWrite() {
		NotCanWriteCommand notCanWriteCommand = new NotCanWriteCommand();
		mViewCommands.beforeApply(notCanWriteCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.notCanWrite();
		}

		mViewCommands.afterApply(notCanWriteCommand);
	}

	@Override
	public  void error() {
		ErrorCommand errorCommand = new ErrorCommand();
		mViewCommands.beforeApply(errorCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.error();
		}

		mViewCommands.afterApply(errorCommand);
	}

	@Override
	public  void showInfo(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
		ShowInfoCommand showInfoCommand = new ShowInfoCommand(messageId, params);
		mViewCommands.beforeApply(showInfoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.MessageView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class LoadMessageCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		public final java.util.List<evfor.fun.skvader.models.Message> messages;

		LoadMessageCommand( java.util.List<evfor.fun.skvader.models.Message> messages) {
			super("loadMessage", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messages = messages;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.loadMessage(messages);
		}
	}

	public class SetTitleCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		public final java.lang.String eventName;

		SetTitleCommand( java.lang.String eventName) {
			super("setTitle", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.eventName = eventName;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.setTitle(eventName);
		}
	}

	public class LoadUserCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		public final evfor.fun.skvader.models.User user;

		LoadUserCommand( evfor.fun.skvader.models.User user) {
			super("loadUser", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.user = user;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.loadUser(user);
		}
	}

	public class NewMessageCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		public final evfor.fun.skvader.models.Message message;

		NewMessageCommand( evfor.fun.skvader.models.Message message) {
			super("newMessage", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.message = message;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.newMessage(message);
		}
	}

	public class UpdateMessageCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		public final evfor.fun.skvader.models.Message message;

		UpdateMessageCommand( evfor.fun.skvader.models.Message message) {
			super("updateMessage", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.message = message;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.updateMessage(message);
		}
	}

	public class RecordCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		public final int time;

		RecordCommand( int time) {
			super("record", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.time = time;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.record(time);
		}
	}

	public class PlayCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		public final int time;
		public final int maxTime;

		PlayCommand( int time,  int maxTime) {
			super("play", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.time = time;
			this.maxTime = maxTime;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.play(time, maxTime);
		}
	}

	public class LoadUsersCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		public final java.util.List<evfor.fun.skvader.models.User> users;

		LoadUsersCommand( java.util.List<evfor.fun.skvader.models.User> users) {
			super("loadUsers", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.users = users;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.loadUsers(users);
		}
	}

	public class WriteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		public final java.lang.String id;

		WriteCommand( java.lang.String id) {
			super("write", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.id = id;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.write(id);
		}
	}

	public class OpenUserCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		public final java.lang.String id;

		OpenUserCommand( java.lang.String id) {
			super("openUser", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.id = id;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.openUser(id);
		}
	}

	public class OpenUsersCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		public final evfor.fun.skvader.models.ActId actId;
		public final boolean c;

		OpenUsersCommand( evfor.fun.skvader.models.ActId actId,  boolean c) {
			super("openUsers", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.actId = actId;
			this.c = c;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.openUsers(actId, c);
		}
	}

	public class ConnectedCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		ConnectedCommand() {
			super("connected", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.connected();
		}
	}

	public class DisconnectedCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		DisconnectedCommand() {
			super("disconnected", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.disconnected();
		}
	}

	public class PermissionDeniedCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		PermissionDeniedCommand() {
			super("permissionDenied", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.permissionDenied();
		}
	}

	public class NotCanWriteCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		NotCanWriteCommand() {
			super("notCanWrite", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.notCanWrite();
		}
	}

	public class ErrorCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		ErrorCommand() {
			super("error", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.error();
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.MessageView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.MessageView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
