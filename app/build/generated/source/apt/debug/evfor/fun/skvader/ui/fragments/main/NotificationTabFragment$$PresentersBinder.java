package evfor.fun.skvader.ui.fragments.main;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class NotificationTabFragment$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.fragments.main.NotificationTabFragment> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.fragments.main.NotificationTabFragment> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.NotificationPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.fragments.main.NotificationTabFragment target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.NotificationPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.fragments.main.NotificationTabFragment delegated) {
			return new evfor.fun.skvader.mvp.presenters.NotificationPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.fragments.main.NotificationTabFragment>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.fragments.main.NotificationTabFragment>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
