package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class IntroActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.IntroActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.IntroActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.IntroPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.IntroActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.IntroPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.IntroActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.IntroPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.IntroActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.IntroActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}