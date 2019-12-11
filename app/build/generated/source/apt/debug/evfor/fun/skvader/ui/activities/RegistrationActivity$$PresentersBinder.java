package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class RegistrationActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.RegistrationActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.RegistrationActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.RegistrationPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.RegistrationActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.RegistrationPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.RegistrationActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.RegistrationPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.RegistrationActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.RegistrationActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
