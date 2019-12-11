package evfor.fun.skvader.ui.activities.search;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class SearchActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.search.SearchActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.search.SearchActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.SearchPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.search.SearchActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.SearchPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.search.SearchActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.SearchPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.search.SearchActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.search.SearchActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
