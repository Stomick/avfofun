package evfor.fun.skvader.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class ActListFragment$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.fragments.ActListFragment> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.fragments.ActListFragment> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.ActListPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.fragments.ActListFragment target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.ActListPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.fragments.ActListFragment delegated) {
			return new evfor.fun.skvader.mvp.presenters.ActListPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.fragments.ActListFragment>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.fragments.ActListFragment>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
