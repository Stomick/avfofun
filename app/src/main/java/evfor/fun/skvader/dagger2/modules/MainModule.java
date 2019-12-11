package evfor.fun.skvader.dagger2.modules;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.pacoworks.rxpaper2.RxPaperBook;
import evfor.fun.skvader.convertors.Converter;
import evfor.fun.skvader.dagger2.qualifiers.PaperBook;
import evfor.fun.skvader.dagger2.scopes.MainScope;
import evfor.fun.skvader.interceptors.ActListProvider;
import evfor.fun.skvader.interceptors.AdministrationAct;
import evfor.fun.skvader.interceptors.CheckAgeRestrictionInteractor;
import evfor.fun.skvader.interceptors.EditPasswordInteractor;
import evfor.fun.skvader.interceptors.FullActInterceptor;
import evfor.fun.skvader.interceptors.GetAgeInteractor;
import evfor.fun.skvader.interceptors.InOutActInteractor;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.interceptors.JointActsInteractor;
import evfor.fun.skvader.interceptors.LoadParticipantsInteractor;
import evfor.fun.skvader.interceptors.MessagesLoaderInteractor;
import evfor.fun.skvader.convertors.NotificationConverter;
import evfor.fun.skvader.interceptors.NotificationsInteractor;
import evfor.fun.skvader.interceptors.SendFeedback;
import evfor.fun.skvader.interceptors.SendSocInteractor;
import evfor.fun.skvader.interceptors.UpdatePhotoInteractor;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActAdmin;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.ActUpdate;
import evfor.fun.skvader.models.AgeRestriction;
import evfor.fun.skvader.models.DistributionParticipant;
import evfor.fun.skvader.models.FeedBack;
import evfor.fun.skvader.models.FullAct;
import evfor.fun.skvader.models.Message;
import evfor.fun.skvader.models.NewPassword;
import evfor.fun.skvader.models.Notification;
import evfor.fun.skvader.models.NotificationCount;
import evfor.fun.skvader.models.NotificationFilter;
import evfor.fun.skvader.models.RqChat;
import evfor.fun.skvader.models.Social;
import evfor.fun.skvader.models.UpdatePhoto;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.network.RequestController;
import evfor.fun.skvader.network.SingleRequest;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.network.models.response.RsBase;
import evfor.fun.skvader.network.models.response.RsNotification;
import evfor.fun.skvader.repository.LocalRepository;
import evfor.fun.skvader.repository.PaperRepos;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.Updater;
import evfor.fun.skvader.repository.data_provide.DataProvide;
import evfor.fun.skvader.repository.data_provide.NotificationCountProvider;
import evfor.fun.skvader.repository.user.UserUpdater;
import evfor.fun.skvader.utils.media.play.AudioPlayer;
import evfor.fun.skvader.utils.media.play.AudioPlayerImpl;
import evfor.fun.skvader.utils.media.record.AudioRecorder;
import evfor.fun.skvader.utils.media.record.AudioRecorderImpl;
import evfor.fun.skvader.utils.socket.SocketMessenger;

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
