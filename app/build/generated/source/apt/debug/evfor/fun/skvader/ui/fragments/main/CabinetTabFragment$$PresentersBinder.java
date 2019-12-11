package evfor.fun.skvader.ui.fragments.main;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class CabinetTabFragment$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.fragments.main.CabinetTabFragment> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.fragments.main.CabinetTabFragment> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.CabinetPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.fragments.main.CabinetTabFragment target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.CabinetPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.fragments.main.CabinetTabFragment delegated) {
			return new evfor.fun.skvader.mvp.presenters.CabinetPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.fragments.main.CabinetTabFragment>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.fragments.main.CabinetTabFragment>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
