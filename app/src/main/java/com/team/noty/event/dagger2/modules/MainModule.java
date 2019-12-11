package com.team.noty.event.dagger2.modules;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.pacoworks.rxpaper2.RxPaperBook;
import com.team.noty.event.convertors.Converter;
import com.team.noty.event.dagger2.qualifiers.PaperBook;
import com.team.noty.event.dagger2.scopes.MainScope;
import com.team.noty.event.interceptors.ActListProvider;
import com.team.noty.event.interceptors.AdministrationAct;
import com.team.noty.event.interceptors.CheckAgeRestrictionInteractor;
import com.team.noty.event.interceptors.EditPasswordInteractor;
import com.team.noty.event.interceptors.FullActInterceptor;
import com.team.noty.event.interceptors.GetAgeInteractor;
import com.team.noty.event.interceptors.InOutActInteractor;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.interceptors.JointActsInteractor;
import com.team.noty.event.interceptors.LoadParticipantsInteractor;
import com.team.noty.event.interceptors.MessagesLoaderInteractor;
import com.team.noty.event.convertors.NotificationConverter;
import com.team.noty.event.interceptors.NotificationsInteractor;
import com.team.noty.event.interceptors.SendFeedback;
import com.team.noty.event.interceptors.SendSocInteractor;
import com.team.noty.event.interceptors.UpdatePhotoInteractor;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActAdmin;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.ActUpdate;
import com.team.noty.event.models.AgeRestriction;
import com.team.noty.event.models.DistributionParticipant;
import com.team.noty.event.models.FeedBack;
import com.team.noty.event.models.FullAct;
import com.team.noty.event.models.Message;
import com.team.noty.event.models.NewPassword;
import com.team.noty.event.models.Notification;
import com.team.noty.event.models.NotificationCount;
import com.team.noty.event.models.NotificationFilter;
import com.team.noty.event.models.RqChat;
import com.team.noty.event.models.Social;
import com.team.noty.event.models.UpdatePhoto;
import com.team.noty.event.models.User;
import com.team.noty.event.network.RequestController;
import com.team.noty.event.network.SingleRequest;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.network.models.response.RsBase;
import com.team.noty.event.network.models.response.RsNotification;
import com.team.noty.event.repository.LocalRepository;
import com.team.noty.event.repository.PaperRepos;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.repository.Updater;
import com.team.noty.event.repository.data_provide.DataProvide;
import com.team.noty.event.repository.data_provide.NotificationCountProvider;
import com.team.noty.event.repository.user.UserUpdater;
import com.team.noty.event.utils.media.play.AudioPlayer;
import com.team.noty.event.utils.media.play.AudioPlayerImpl;
import com.team.noty.event.utils.media.record.AudioRecorder;
import com.team.noty.event.utils.media.record.AudioRecorderImpl;
import com.team.noty.event.utils.socket.SocketMessenger;

import java.util.Calendar;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

@Module
public interface MainModule {

    @Provides
    @MainScope
    @PaperBook(PaperBook.TYPE.USERS)
    static RxPaperBook provideBook() {
        return RxPaperBook.with(User.TAG, Schedulers.io());
    }

    @Provides
    @MainScope
    @PaperBook(PaperBook.TYPE.EVENTCOMM)
    static RxPaperBook provideBookEventComm() {
        return RxPaperBook.with(Act.TAG, Schedulers.io());
    }

    @Provides
    @MainScope
    @PaperBook(PaperBook.TYPE.BAN)
    static RxPaperBook provideBookBanEventComm() {
        return RxPaperBook.with(Act.TAG, Schedulers.io());
    }

    @Provides
    @MainScope
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

    @Binds
    @MainScope
    Interactor<Single<FullAct>, ActId> provideFullActInterceptor(FullActInterceptor interceptor);

    @Binds
    @MainScope
    Interactor<Single<ActId>, String> provideJointActInteractor(JointActsInteractor interceptor);

    @Binds
    @MainScope
    LocalRepository<User, String> provideUserBook(PaperRepos repos);

    @Binds
    @MainScope
    Interactor<Completable, ActUpdate> privodeJoinAct(InOutActInteractor inOutActInteractor);

    @Binds
    @MainScope
    Interactor<Observable<? extends User>, ActAdmin> privodeParticipant(LoadParticipantsInteractor loadParticipantsInteractor);

    @Binds
    @MainScope
    Interactor<Completable, AgeRestriction> privodeCheckAgeRestrictionInteractor(CheckAgeRestrictionInteractor checkAgeRestrictionInteractor);

    @Binds
    @MainScope
    Interactor<Integer, Calendar> provideGetAgeInteractor(GetAgeInteractor getAgeInteractor);

    @Binds
    @MainScope
    Interactor<Completable, DistributionParticipant> provideAdministrationAct(AdministrationAct administrationAct);

    @Provides
    @MainScope
    static SocketMessenger provideSocketMessanger() {
        return new SocketMessenger();
    }

    @Binds
    @MainScope
    Interactor<Observable<Message>, RqChat> provideMessageLoader(MessagesLoaderInteractor messagesLoaderInteractor);

    @Binds
    @MainScope
    AudioPlayer provideAudioPlayer(AudioPlayerImpl audioPlayer);

    @Binds
    @MainScope
    AudioRecorder provideAudioRecorder(AudioRecorderImpl audioRecorder);

    @Binds
    @MainScope
    Interactor<Completable, UpdatePhoto> provideUpdatePhoto(UpdatePhotoInteractor interactor);

    @Binds
    @MainScope
    Interactor<Completable, NewPassword> provideNewPassword(EditPasswordInteractor interactor);

    @Binds
    @MainScope
    Updater<User, Integer> provideUserUpdater(UserUpdater userUpdater);

    @Binds
    @MainScope
    Interactor<Completable, Social> sendSocial(SendSocInteractor interactor);


    @Binds
    @MainScope
    ReaderRepos<List<Act>, User> provideActListReader(ActListProvider provider);

    @Binds
    @MainScope
    Interactor<Single<RsBase>, FeedBack> provideFeedBackSender(SendFeedback sendFeedback);

    @MainScope
    @Binds
    Interactor<Observable<Notification>, NotificationFilter> provideNotovications(NotificationsInteractor interactor);

    @MainScope
    @Binds
    Converter<Notification, RsNotification.Notification> provideNotificationConverter(NotificationConverter converter);

    @MainScope
    @Binds
    RequestController<ActId, Act> provideActRqController(SingleRequest<ActId, Act> singleRequest);

    @MainScope
    @Binds
    RequestController<Integer, User> provideUserRqController(SingleRequest<Integer, User> singleRequest);

    @MainScope
    @Provides
    static NotificationManager provideNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @MainScope
    @Provides
    static NotificationCompat.Builder provideNotificationCompatBuilder(Context context) {
        return new NotificationCompat.Builder(context, "1");
    }

    @MainScope
    @Binds
    DataProvide<NotificationCount> notificationCountProvider(NotificationCountProvider provider);
}
