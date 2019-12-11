package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class MapActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.MapActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.MapActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.MapPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.MapActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.MapPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.MapActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.MapPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.MapActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.MapActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
