package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class ChangePasswordActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.ChangePasswordActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.ChangePasswordActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.ChangePasswordPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.ChangePasswordActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.ChangePasswordPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.ChangePasswordActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.ChangePasswordPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.ChangePasswordActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.ChangePasswordActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
