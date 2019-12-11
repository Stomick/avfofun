package evfor.fun.skvader.ui.fragments.main;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class SearchTabFragment$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.fragments.main.SearchTabFragment> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.fragments.main.SearchTabFragment> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.SearchTabPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.fragments.main.SearchTabFragment target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.SearchTabPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.fragments.main.SearchTabFragment delegated) {
			return new evfor.fun.skvader.mvp.presenters.SearchTabPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.fragments.main.SearchTabFragment>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.fragments.main.SearchTabFragment>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
