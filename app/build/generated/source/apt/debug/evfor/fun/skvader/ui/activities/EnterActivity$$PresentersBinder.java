package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class EnterActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.EnterActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.EnterActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.LoginPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.EnterActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.LoginPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.EnterActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.LoginPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.EnterActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.EnterActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}