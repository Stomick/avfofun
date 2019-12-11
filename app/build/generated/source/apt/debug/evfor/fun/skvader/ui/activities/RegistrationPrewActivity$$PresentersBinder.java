package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class RegistrationPrewActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.RegistrationPrewActivity> {
	public class prewPresenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.RegistrationPrewActivity> {
		public prewPresenterBinder() {
			super("prewPresenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.RegPrewPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.RegistrationPrewActivity target, MvpPresenter presenter) {
			target.prewPresenter = (evfor.fun.skvader.mvp.presenters.RegPrewPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.RegistrationPrewActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.RegPrewPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.RegistrationPrewActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.RegistrationPrewActivity>> presenters = new ArrayList<>();

		presenters.add(new prewPresenterBinder());

		return presenters;
	}

}
