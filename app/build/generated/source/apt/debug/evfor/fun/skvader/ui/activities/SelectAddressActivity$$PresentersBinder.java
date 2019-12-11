package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class SelectAddressActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.SelectAddressActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.SelectAddressActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.SelectAddressPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.SelectAddressActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.SelectAddressPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.SelectAddressActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.SelectAddressPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.SelectAddressActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.SelectAddressActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
