package evfor.fun.skvader.ui.activities.search;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class FilterActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.search.FilterActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.search.FilterActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.FilterPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.search.FilterActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.FilterPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.search.FilterActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.FilterPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.search.FilterActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.search.FilterActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
