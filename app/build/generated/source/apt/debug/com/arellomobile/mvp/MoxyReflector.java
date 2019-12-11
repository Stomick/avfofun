package com.arellomobile.mvp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoxyReflector {

	private static Map<Class<?>, Object> sViewStateProviders;
	private static Map<Class<?>, List<Object>> sPresenterBinders;
	private static Map<Class<?>, Object> sStrategies;

	static {
		sViewStateProviders = new HashMap<>();
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.ActListPresenter.class, new evfor.fun.skvader.mvp.presenters.ActListPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.CabinetPresenter.class, new evfor.fun.skvader.mvp.presenters.CabinetPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.ChangePasswordPresenter.class, new evfor.fun.skvader.mvp.presenters.ChangePasswordPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.ComplaintPresenter.class, new evfor.fun.skvader.mvp.presenters.ComplaintPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.CreateEventCommPresenter.class, new evfor.fun.skvader.mvp.presenters.CreateEventCommPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.DialogPresenter.class, new evfor.fun.skvader.mvp.presenters.DialogPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.EditCategoriesPresenter.class, new evfor.fun.skvader.mvp.presenters.EditCategoriesPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.EditProfilePresenter.class, new evfor.fun.skvader.mvp.presenters.EditProfilePresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.EventPresenter.class, new evfor.fun.skvader.mvp.presenters.EventPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.FavoritePresenter.class, new evfor.fun.skvader.mvp.presenters.FavoritePresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.FilterPresenter.class, new evfor.fun.skvader.mvp.presenters.FilterPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.GiveFeedbackPresenter.class, new evfor.fun.skvader.mvp.presenters.GiveFeedbackPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.IntroPresenter.class, new evfor.fun.skvader.mvp.presenters.IntroPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.LoginPresenter.class, new evfor.fun.skvader.mvp.presenters.LoginPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.MapPresenter.class, new evfor.fun.skvader.mvp.presenters.MapPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.NotificationPresenter.class, new evfor.fun.skvader.mvp.presenters.NotificationPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.PartsPresenter.class, new evfor.fun.skvader.mvp.presenters.PartsPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.RegPrewPresenter.class, new evfor.fun.skvader.mvp.presenters.RegPrewPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.RegistrationPresenter.class, new evfor.fun.skvader.mvp.presenters.RegistrationPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.RestorePresenter.class, new evfor.fun.skvader.mvp.presenters.RestorePresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.SearchPresenter.class, new evfor.fun.skvader.mvp.presenters.SearchPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.SearchTabPresenter.class, new evfor.fun.skvader.mvp.presenters.SearchTabPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.SelectAddressPresenter.class, new evfor.fun.skvader.mvp.presenters.SelectAddressPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.SelectCityPresenter.class, new evfor.fun.skvader.mvp.presenters.SelectCityPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.SelectLocationPresenter.class, new evfor.fun.skvader.mvp.presenters.SelectLocationPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.SocPresenter.class, new evfor.fun.skvader.mvp.presenters.SocPresenter$$ViewStateProvider());
		sViewStateProviders.put(evfor.fun.skvader.mvp.presenters.TabsPresenter.class, new evfor.fun.skvader.mvp.presenters.TabsPresenter$$ViewStateProvider());
		
		sPresenterBinders = new HashMap<>();
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.AccessRecoveryActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.AccessRecoveryActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.ChangePasswordActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.ChangePasswordActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.ComplaintActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.ComplaintActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.DialogActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.DialogActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.EditCategoriesActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.EditCategoriesActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.EditProfileActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.EditProfileActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.EnterActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.EnterActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.EventActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.EventActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.GiveFeedbackActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.GiveFeedbackActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.IntroActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.IntroActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.MapActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.MapActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.MySocNetActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.MySocNetActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.ParticipantsActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.ParticipantsActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.RegistrationActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.RegistrationActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.RegistrationPrewActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.RegistrationPrewActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.SelectAddressActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.SelectAddressActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.SelectCityActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.SelectCityActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.SelectLocationActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.SelectLocationActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.TabsActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.TabsActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.search.FilterActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.search.FilterActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.activities.search.SearchActivity.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.activities.search.SearchActivity$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.fragments.ActListFragment.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.fragments.ActListFragment$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.fragments.main.CabinetTabFragment.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.fragments.main.CabinetTabFragment$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.fragments.main.FavoriteTabFragment.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.fragments.main.FavoriteTabFragment$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.fragments.main.NotificationTabFragment.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.fragments.main.NotificationTabFragment$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.fragments.main.SearchTabFragment.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.fragments.main.SearchTabFragment$$PresentersBinder()));
		sPresenterBinders.put(evfor.fun.skvader.ui.fragments.main.create.BaseCreateFragment.class, Arrays.<Object>asList(new evfor.fun.skvader.ui.fragments.main.create.BaseCreateFragment$$PresentersBinder()));
		
		sStrategies = new HashMap<>();
		sStrategies.put(com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy.class, new com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy());
		sStrategies.put(com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy.class, new com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy());
	}
	
	public static Object getViewState(Class<?> presenterClass) {
		ViewStateProvider viewStateProvider = (ViewStateProvider) sViewStateProviders.get(presenterClass);
		if (viewStateProvider == null) {
			return null;
		}
		
		return viewStateProvider.getViewState();
	}

	public static List<Object> getPresenterBinders(Class<?> delegated) {
		return sPresenterBinders.get(delegated);
	}

	public static Object getStrategy(Class<?> strategyClass) {
		return sStrategies.get(strategyClass);
	}
}
