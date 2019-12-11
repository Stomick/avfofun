package com.team.noty.event.dagger2.components;

import com.team.noty.event.dagger2.modules.ChatModule;
import com.team.noty.event.dagger2.modules.MainModule;
import com.team.noty.event.dagger2.modules.RepositoryModule;
import com.team.noty.event.dagger2.scopes.MainScope;
import com.team.noty.event.mvp.presenters.CabinetPresenter;
import com.team.noty.event.mvp.presenters.ChangePasswordPresenter;
import com.team.noty.event.mvp.presenters.ComplaintPresenter;
import com.team.noty.event.mvp.presenters.CreateEventCommPresenter;
import com.team.noty.event.mvp.presenters.EditCategoriesPresenter;
import com.team.noty.event.mvp.presenters.EditProfilePresenter;
import com.team.noty.event.mvp.presenters.ActListPresenter;
import com.team.noty.event.mvp.presenters.EventPresenter;
import com.team.noty.event.mvp.presenters.DialogPresenter;
import com.team.noty.event.mvp.presenters.FavoritePresenter;
import com.team.noty.event.mvp.presenters.GiveFeedbackPresenter;
import com.team.noty.event.mvp.presenters.NotificationPresenter;
import com.team.noty.event.mvp.presenters.PartsPresenter;
import com.team.noty.event.mvp.presenters.RegPrewPresenter;
import com.team.noty.event.mvp.presenters.SearchPresenter;
import com.team.noty.event.mvp.presenters.SearchTabPresenter;
import com.team.noty.event.mvp.presenters.SocPresenter;
import com.team.noty.event.mvp.presenters.TabsPresenter;
import com.team.noty.event.ui.activities.ReviewActivity;
import com.team.noty.event.ui.utils.reg_step_view.Step2;
import com.team.noty.event.utils.notification.NotificationsUtils;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = {MainModule.class, RepositoryModule.class})
@MainScope
public interface MainComponent {
    ChatSubComponent chatSubComponent(ChatModule chatModule);

    void inject(SearchTabPresenter presenter);
    void inject(TabsPresenter presenter);
    void inject(CabinetPresenter presenter);
    void inject(CreateEventCommPresenter presenter);
    void inject(EditProfilePresenter presenter);
    void inject(EventPresenter presenter);
    void inject(ChangePasswordPresenter presenter);
    void inject(SearchPresenter presenter);
    void inject(RegPrewPresenter presenter);
    void inject(EditCategoriesPresenter presenter);
    void inject(DialogPresenter presenter);
    void inject(PartsPresenter presenter);
    void inject(ComplaintPresenter presenter);
    void inject(FavoritePresenter presenter);
    void inject(ActListPresenter presenter);
    void inject(NotificationPresenter presenter);
    void inject(NotificationsUtils utils);
    void inject(SocPresenter presenter);
    void inject(GiveFeedbackPresenter presenter);
    void inject(Step2 step2);
    void inject(ReviewActivity reviewActivity);
}
