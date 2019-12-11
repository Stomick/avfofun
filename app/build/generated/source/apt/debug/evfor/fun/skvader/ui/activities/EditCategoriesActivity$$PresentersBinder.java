package evfor.fun.skvader.ui.activities;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class EditCategoriesActivity$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.activities.EditCategoriesActivity> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.activities.EditCategoriesActivity> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.EditCategoriesPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.activities.EditCategoriesActivity target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.EditCategoriesPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.activities.EditCategoriesActivity delegated) {
			return new evfor.fun.skvader.mvp.presenters.EditCategoriesPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.activities.EditCategoriesActivity>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.activities.EditCategoriesActivity>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
