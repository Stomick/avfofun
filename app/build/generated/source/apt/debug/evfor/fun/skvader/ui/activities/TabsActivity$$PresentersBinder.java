package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class TabsActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.TabsActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.TabsActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.TabsPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.TabsActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.TabsPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.TabsActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.TabsPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.TabsActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.TabsActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
