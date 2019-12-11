package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class ComplaintActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.ComplaintActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.ComplaintActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.ComplaintPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.ComplaintActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.ComplaintPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.ComplaintActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.ComplaintPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.ComplaintActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.ComplaintActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
