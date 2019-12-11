package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class GiveFeedbackActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.GiveFeedbackActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.GiveFeedbackActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.GiveFeedbackPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.GiveFeedbackActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.GiveFeedbackPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.GiveFeedbackActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.GiveFeedbackPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.GiveFeedbackActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.GiveFeedbackActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
