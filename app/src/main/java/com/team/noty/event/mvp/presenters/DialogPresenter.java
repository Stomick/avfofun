package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.app.Injector;
import com.team.noty.event.exceptions.NotFoundException;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActAdmin;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.Message;
import com.team.noty.event.models.RqChat;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.views.MessageView;
import com.team.noty.event.repository.Identified;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.utils.BitmapUtils;
import com.team.noty.event.utils.DateFormatter;
import com.team.noty.event.utils.FilesUtils;
import com.team.noty.event.utils.ListUtils;
import com.team.noty.event.utils.Pair;
import com.team.noty.event.utils.PermissionController;
import com.team.noty.event.utils.StringUtils;
import com.team.noty.event.utils.UrlUtil;
import com.team.noty.event.utils.media.play.AudioPlayer;
import com.team.noty.event.utils.media.record.AudioRecorder;
import com.team.noty.event.utils.socket.SocketMessenger;
import com.team.noty.event.utils.socket.models.MessageReceive;
import com.team.noty.event.utils.socket.models.SocketState;
import com.team.noty.event.utils.socket.models.Writer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class DialogPresenter extends BasePresenter<MessageView> {

    @Inject
    SocketMessenger messenger;
    @Inject
    PermissionController permissionController;
    @Inject
    Interactor<Observable<? extends User>, ActAdmin> usersLoader;
    @Inject
    Interactor<Observable<Message>, RqChat> messagesLoader;
    @Inject
    ReaderRepos<User, Integer> profileLoader;
    @Inject
    Interactor<Single<ActId>, String> jointAct;
    @Inject
    ReaderRepos<Act, ActId> actLoader;
    @Inject
    AudioRecorder audioRecorder;
    @Inject
    AudioPlayer audioPlayer;

    public ActId id;
    private User current;
    private List<User> users;
    private ActId joinActId;
    private boolean creator;

    public DialogPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void socketDisconnect() {
        messenger.disconnect();
    }

    public void openUsers() {
        if (current != null)
            getViewState().openUser(String.valueOf(current.id));
        else getViewState().openUsers(joinActId, creator);
    }

    public void loadUser(String id) {
        profileLoader.request(StringUtils.toIntOr0(id))
                .doOnSuccess(this::setCurrent)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(getViewState()::loadUser)
                .observeOn(Schedulers.computation())
                .flatMap(user -> jointAct.call(user.id())
                        .zipWith(Single.just((Identified) user), RqChat::new))
                .flatMapObservable(this::messageLoader)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::loadMessage,
                        this::errorHandler);
    }

    private void errorHandler(Throwable throwable){
        //if(throwable instanceof NoJointActsException)
            getViewState().notCanWrite();
    }

    private void setCurrent(User current) {
        this.current = current;
    }

    public void loadUsers(ActId actId) {

        joinActId = actId;
        usersLoader.call(new ActAdmin(actId.id(), actId.isEvent, false))
                //load users
                .doOnNext(this::addUser)
                .map(o -> (User) o)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(getViewState()::loadUsers)
                //load title
                .observeOn(Schedulers.io())
                .toCompletable()
                .andThen(actLoader.request(actId))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(this::setTitle)
                .observeOn(Schedulers.io())
                .toCompletable()
                //load messages
                .andThen(messageLoader(new RqChat(actId)))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::loadMessage,this::errorHandler);
    }

    private Observable<Message> messageLoader(RqChat rqChat) {
        if (current != null)
            messenger.init(rqChat.actId.id(), String.valueOf(current.id), rqChat.actId.isEvent);
        else
            messenger.init(rqChat.actId.id(), rqChat.actId.isEvent);
        connect();
        return messagesLoader.call(rqChat);
    }

    private void setTitle(Act act) {
        getViewState().setTitle(act.title);
        creator = AuthData.equalId(act.user_id);
    }

    private void addUser(User user) {
        if (users == null)
            users = new ArrayList<>();
        if (!users.contains(user))
            users.add(user);
    }

    private void connect() {
        onStatus();
        onMessage();
        onReadMessage();
        onWrite();
        messenger.connect();
    }

    private void onStatus() {
        messenger.socketStateObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setStatus);
    }

    private void setStatus(SocketState status) {
        if (status.isConnected)
            getViewState().connected();
        else getViewState().disconnected();
    }

    public void write() {
        messenger.writeEmit();
    }

    private void onReadMessage() {
        subIoObsMain(messenger.readMessageObservable())
                .map(messageListReaded -> messageListReaded.messages)
                .flatMap(Observable::fromIterable)
                .map(s -> new Message(s, Message.Status.READ))
                .subscribe(s -> getViewState().updateMessage(s), this::onError);
    }

    private void onWrite() {
        messenger.writeObserver()
                .observeOn(AndroidSchedulers.mainThread())
                .map(Writer::id)
                .subscribe(getViewState()::write);
    }

    private void onMessage() {
        subIoObsMain(messenger.messageObservable())
                .doOnNext(this::setName)
                .subscribe(
                        uiMessage -> getViewState().newMessage(uiMessage), this::onError);
    }

    private void setName(Message message) throws NotFoundException {
        String name;
        if (current != null)
            name = current.firstname;
        else name = ListUtils.search(users, message.sender).firstname;
        message.name = name;
    }

    public void sendMessage(String text, int update) {
        sentMessageHandle(messenger.sendTextMessage(text, update));
    }

    public void sendPhoto(String path, int update) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        if (bitmap != null)
            sentMessageHandle(messenger.sendPhotoMessage(BitmapUtils.getBitmapBase64(bitmap), update));
    }

    private void sentMessageHandle(Single<MessageReceive> messageReceiveSingle) {
        messageReceiveSingle
                .map(messageReceive -> new Message(
                        messageReceive.messageId,
                        messageReceive.update,
                        DateFormatter.fromIso(messageReceive.date),
                        Message.Type.TEXT))
                .doOnSuccess(Message::toDeliver)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::updateMessage, super::onError);
    }

    public int createId() {
        return Math.abs(new Random().nextInt());
    }

    public void startRecord() {
        if (!audioRecorder.isRec())
            sentMessageHandle(audioRecorder.start()
                    .observeOn(Schedulers.computation())
                    .flatMap(this::toBytes)
                    .flatMap(this::sendVoice));
    }

    private Single<Pair<byte[], String>> toBytes(File file) {
        return Single.just(file)
                .subscribeOn(Schedulers.computation())
                .map(FilesUtils::toBytes)
                .zipWith(Single.just(file.getAbsolutePath()), Pair::new);
    }

    private Single<MessageReceive> sendVoice(Pair<byte[], String> pair) {
        Message message = new Message();
        message.status = Message.Status.SEND;
        message.type = Message.Type.VOICE;
        message.updateId = createId();
        message.body = pair.getRight();
        message.sender = AuthData.getID();
        Single.just(message)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::newMessage);
        return messenger
                .sendVoiceMessage(pair.getLeft(), message.updateId);
    }

    public void cancelRec() {
        audioRecorder.cancel();
    }

    public void sendRecord() {
        audioRecorder.end();
    }

    public void play(String path) {
        path = UrlUtil.correct(path);
        audioRecorder.cancel();
        audioPlayer.onPlay();
        if (!audioPlayer.onPlay())
            audioPlayer.play(path)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            integer ->
                            {
                                Log.i("onPrepare","3");
                                Log.i("onPrepare","-----------");
                                getViewState().play(integer, audioPlayer.duration());
                            },
                            this::onError,
                            () ->
                            {
                                Log.i("onPrepare","4");
                                getViewState().play(-1, 0);
                            });
        else
            audioPlayer.onResumePause();
    }

    public void stopPlay() {
        audioPlayer.stop();
    }

    public void pausePlay() {
        audioPlayer.onResumePause();
    }
    public boolean isPlaying()
    {
        return audioPlayer.onPlay();
    }

    public void readMessages(List<Message> messages) {
        Observable.fromIterable(createPacks(messages))
                .doOnNext(
                        readMessagePack -> messenger.readMessages(readMessagePack.messageIds, readMessagePack.userId))
                .toList()
                .toCompletable()
                .subscribe(() -> {

                    },
                        thr ->Log.e("my",thr.getMessage()));
    }

    private List<ReadMessagePack> createPacks(List<Message> messages) {
        List<ReadMessagePack> packs = new ArrayList<>();
        for (Message m : messages) {
            ReadMessagePack p = getById(packs, m.sender);
            if (p == null) {
                p = new ReadMessagePack(m.sender);
                packs.add(p);
            }
            p.messageIds.add(m.id);
        }
        return packs;
    }

    private ReadMessagePack getById(List<ReadMessagePack> packs, String userId) {
        for (ReadMessagePack p : packs)
            if (p.userId.equals(userId))
                return p;
        return null;
    }

    private class ReadMessagePack {
        List<String> messageIds;
        String userId;

        ReadMessagePack(String userId) {
            this.userId = userId;
            this.messageIds = new ArrayList<>();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ReadMessagePack && ((ReadMessagePack) obj).userId != null && userId != null)
                return ((ReadMessagePack) obj).userId.equals(userId);
            if (obj instanceof String && userId != null)
                return userId.equals(obj);
            return super.equals(obj);
        }
    }

    @Override
    void onError(Throwable throwable) {
        super.onError(throwable);
        throwable.printStackTrace();
        cancelRec();
        stopPlay();
    }

    @Override
    public void onDestroy() {
        stopPlay();
        cancelRec();
        super.onDestroy();
        Injector.get().closeChatComponent();
    }
}
