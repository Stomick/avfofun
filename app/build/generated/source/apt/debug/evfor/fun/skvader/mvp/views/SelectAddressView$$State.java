package evfor.fun.skvader.mvp.views;

import java.util.Set;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.ViewCommands;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

public class SelectAddressView$$State extends MvpViewState<evfor.fun.skvader.mvp.views.SelectAddressView> implements evfor.fun.skvader.mvp.views.SelectAddressView {

	@Override
	public  void setAddressList( java.util.List<evfor.fun.skvader.models.Address> addresses) {
		SetAddressListCommand setAddressListCommand = new SetAddressListCommand(addresses);
		mViewCommands.beforeApply(setAddressListCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.SelectAddressView view : mViews) {
			view.setAddressList(addresses);
		}

		mViewCommands.afterApply(setAddressListCommand);
	}

	@Override
	public  void showInfo(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
		ShowInfoCommand showInfoCommand = new ShowInfoCommand(messageId, params);
		mViewCommands.beforeApply(showInfoCommand);

		if (mViews == null || mViews.isEmpty()) {
			return;
		}

		for(evfor.fun.skvader.mvp.views.SelectAddressView view : mViews) {
			view.showInfo(messageId, params);
		}

		mViewCommands.afterApply(showInfoCommand);
	}


	public class SetAddressListCommand extends ViewCommand<evfor.fun.skvader.mvp.views.SelectAddressView> {
		public final java.util.List<evfor.fun.skvader.models.Address> addresses;

		SetAddressListCommand( java.util.List<evfor.fun.skvader.models.Address> addresses) {
			super("setAddressList", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.addresses = addresses;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.SelectAddressView mvpView) {
			mvpView.setAddressList(addresses);
		}
	}

	public class ShowInfoCommand extends ViewCommand<evfor.fun.skvader.mvp.views.SelectAddressView> {
		public final int messageId;
		public final java.lang.Object[] params;

		ShowInfoCommand(@android.support.annotation.StringRes int messageId,  java.lang.Object[] params) {
			super("showInfo", com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class);
			this.messageId = messageId;
			this.params = params;
		}

		@Override
		public void apply(evfor.fun.skvader.mvp.views.SelectAddressView mvpView) {
			mvpView.showInfo(messageId, params);
		}
	}
}
