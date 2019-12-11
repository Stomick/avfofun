package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class EditProfileActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.EditProfileActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.EditProfileActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.EditProfilePresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.EditProfileActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.EditProfilePresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.EditProfileActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.EditProfilePresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.EditProfileActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.EditProfileActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
