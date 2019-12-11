package evfor.fun.skvader.dagger2.components;

import evfor.fun.skvader.dagger2.modules.ChatModule;
import evfor.fun.skvader.dagger2.modules.MainModule;
import evfor.fun.skvader.dagger2.modules.RepositoryModule;
import evfor.fun.skvader.dagger2.scopes.MainScope;
import evfor.fun.skvader.mvp.presenters.CabinetPresenter;
import evfor.fun.skvader.mvp.presenters.ChangePasswordPresenter;
import evfor.fun.skvader.mvp.presenters.ComplaintPresenter;
import evfor.fun.skvader.mvp.presenters.CreateEventCommPresenter;
import evfor.fun.skvader.mvp.presenters.EditCategoriesPresenter;
import evfor.fun.skvader.mvp.presenters.EditProfilePresenter;
import evfor.fun.skvader.mvp.presenters.ActListPresenter;
import evfor.fun.skvader.mvp.presenters.EventPresenter;
import evfor.fun.skvader.mvp.presenters.DialogPresenter;
import evfor.fun.skvader.mvp.presenters.FavoritePresenter;
import evfor.fun.skvader.mvp.presenters.GiveFeedbackPresenter;
import evfor.fun.skvader.mvp.presenters.NotificationPresenter;
import evfor.fun.skvader.mvp.presenters.PartsPresenter;
import evfor.fun.skvader.mvp.presenters.RegPrewPresenter;
import evfor.fun.skvader.mvp.presenters.SearchPresenter;
import evfor.fun.skvader.mvp.presenters.SearchTabPresenter;
import evfor.fun.skvader.mvp.presenters.SocPresenter;
import evfor.fun.skvader.mvp.presenters.TabsPresenter;
import evfor.fun.skvader.ui.activities.ReviewActivity;
import evfor.fun.skvader.ui.utils.reg_step_view.Step2;
import evfor.fun.skvader.utils.notification.NotificationsUtils;

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
