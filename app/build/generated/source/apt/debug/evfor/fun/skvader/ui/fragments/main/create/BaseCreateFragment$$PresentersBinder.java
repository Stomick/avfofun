package evfor.fun.skvader.ui.fragments.main.create;

import java.util.ArrayList;
import java.util.List;

import com.arellomobile.mvp.PresenterBinder;
import com.arellomobile.mvp.presenter.PresenterField;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class BaseCreateFragment$$PresentersBinder extends PresenterBinder<evfor.fun.skvader.ui.fragments.main.create.BaseCreateFragment> {
	public class presenterBinder extends PresenterField<evfor.fun.skvader.ui.fragments.main.create.BaseCreateFragment> {
		public presenterBinder() {
			super("presenter", PresenterType.LOCAL, null, evfor.fun.skvader.mvp.presenters.CreateEventCommPresenter.class);
		}

		@Override
		public void bind(evfor.fun.skvader.ui.fragments.main.create.BaseCreateFragment target, MvpPresenter presenter) {
			target.presenter = (evfor.fun.skvader.mvp.presenters.CreateEventCommPresenter) presenter;
		}

		@Override
		public MvpPresenter<?> providePresenter(evfor.fun.skvader.ui.fragments.main.create.BaseCreateFragment delegated) {
			return new evfor.fun.skvader.mvp.presenters.CreateEventCommPresenter();
		}
	}

	public List<PresenterField<evfor.fun.skvader.ui.fragments.main.create.BaseCreateFragment>> getPresenterFields() {
		List<PresenterField<evfor.fun.skvader.ui.fragments.main.create.BaseCreateFragment>> presenters = new ArrayList<>();

		presenters.add(new presenterBinder());

		return presenters;
	}

}
