package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class SelectCityActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.SelectCityActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.SelectCityActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.SelectCityPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.SelectCityActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.SelectCityPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.SelectCityActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.SelectCityPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.SelectCityActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.SelectCityActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
