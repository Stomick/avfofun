package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class MySocNetActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.MySocNetActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.MySocNetActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.SocPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.MySocNetActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.SocPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.MySocNetActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.SocPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.MySocNetActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.MySocNetActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
