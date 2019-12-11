package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class ParticipantsActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.ParticipantsActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.ParticipantsActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.PartsPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.ParticipantsActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.PartsPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.ParticipantsActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.PartsPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.ParticipantsActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.ParticipantsActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
