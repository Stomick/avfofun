package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class SelectLocationActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.SelectLocationActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.SelectLocationActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.SelectLocationPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.SelectLocationActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.SelectLocationPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.SelectLocationActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.SelectLocationPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.SelectLocationActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.SelectLocationActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
