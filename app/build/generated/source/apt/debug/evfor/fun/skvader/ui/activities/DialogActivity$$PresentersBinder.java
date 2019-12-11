package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class DialogActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.DialogActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.DialogActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.DialogPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.DialogActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.DialogPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.DialogActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.DialogPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.DialogActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.DialogActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
