package evfor.fun.skvader.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import evfor.fun.skvader.R;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.Message;
import evfor.fun.skvader.models.SockMessage;
import evfor.fun.skvader.models.SockMessages;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.mvp.presenters.DialogPresenter;
import evfor.fun.skvader.mvp.presenters.LoginPresenter;
import evfor.fun.skvader.mvp.views.MessageView;
import evfor.fun.skvader.ui.adapters.MessageAdapter;
import evfor.fun.skvader.ui.dialogs.DialogProvider;
import evfor.fun.skvader.ui.dialogs.Toaster;
import evfor.fun.skvader.ui.holders.InputPanelHolder;
import evfor.fun.skvader.ui.models.ChatRoom;
import evfor.fun.skvader.ui.utils.RecyclerUtils;
import evfor.fun.skvader.utils.ImageLoader;
import evfor.fun.skvader.utils.RealPathUtil;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.OnClick;
import evfor.fun.skvader.utils.socket.SocketChat;
import io.reactivex.Maybe;
import io.socket.emitter.Emitter;

@SuppressLint("CheckResult")
public class DialogActivity extends BaseActivity implements MessageView {

    private static final String ID = "id";
    private static String userTo = null;
    private static String roomType = "event";
    private static int room_id = 0;
    private static String title = null;
    @InjectPresenter
    DialogPresenter presenter;

    @BindView(R.id.dialog_message_list)
    RecyclerView dialogMessageList;
    @BindView(R.id.input_panel)
    View inputPanel;
    InputPanelHolder inputPanelHolder;
    List<User> users;
    List<String> user_ids = new ArrayList<>();

    List<SockMessage> mess;

    private MessageAdapter adapter;
    private ImageView ava, ava1, ava2;
    public static boolean isNeedToConnect = true;
    public static String creator_name = null;


    public static void openDialog(Context context, String userID) {
        Intent i = intent(context, userID);
        if (i != null) {
            roomType = "user";
            SocketChat socketChat = SocketChat.getInstance();
            JSONObject chObj = new JSONObject();

            try {
                chObj.put("userTo", userID);
                chObj.put("type", roomType);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            socketChat.getChat().emit("getRoom", chObj).on("getRoom", new GetRoom());
            context.startActivity(i);
        }
    }

    public static Intent intent(Context context, String userID) {
        if (AuthData.notEqualId(userID)) {
            return new Intent(context, DialogActivity.class)
                    .putExtra(ID, userID);
        } else return null;
    }

    public static void openChat(Context context, ActId actId, String name, String id) {
        if (actId.isEvent) {
            roomType = "event";
        } else {
            roomType = "community";
        }
        room_id = actId.room_id;
        title = name;
        context.startActivity(new Intent(context, DialogActivity.class)
                .putExtra(ActId.TAG, actId)
                .putExtra("name", name)
                .putExtra("id", id));
    }

    @Override
    protected int layout() {
        return R.layout.activity_dialog;
    }

    class NewMessage implements Emitter.Listener {

        @Override
        public void call(Object... args) {

            Gson gson = new GsonBuilder().create();
            String bs = args[0].toString();
            SockMessages newMess = gson.fromJson(args[0].toString(), SockMessages.class);
            if (mess == null) {
                mess = new ArrayList<>();
            }
            for (int i = 0; i < newMess.size(); i++) {
                mess.add(newMess.messages.get(i));
            }
            if (mess != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("logs", mess.toString());
                        adapter.setMessages(mess);
                        scrollBot();
                    }
                });

                //setReadMessages(mess);
            }

        }

    }

    static class GetRoom implements Emitter.Listener {

        @Override
        public void call(Object... args) {

            Gson gson = new GsonBuilder().create();
            String bs = args[0].toString();
            ChatRoom chatRoom = gson.fromJson(args[0].toString(), ChatRoom.class);
            room_id = chatRoom.roomId;
            title = chatRoom.name;
        }

    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        adapter = new MessageAdapter();
        SocketChat socketChat = SocketChat.getInstance();
        JSONObject chObj = new JSONObject();

        if (socketChat.getChat() != null) {
            try {
                chObj.put("roomId", room_id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            socketChat.getChat().emit("joinToRoom", chObj).on("message", new NewMessage());

            RecyclerUtils.setMessageList(dialogMessageList, adapter);
            inputPanel.setVisibility(View.VISIBLE);
            inputPanelHolder = new InputPanelHolder(inputPanel);
            inputPanelHolder.setWriting()
                    .subscribe(v -> socketChat.getChat().emit("writes", chObj));
            dialogMessageList.getItemAnimator().setChangeDuration(0);
        /*
        adapter = new MessageAdapter();

        isNeedToConnect = true;
        adapter.setOnPlay(presenter::play);
        adapter.setOnPause(presenter::pausePlay);
        */
            //showLoad();
            //hideLoad();
        }
        setTitle(title);
    }

    @Override
    protected void toggleSoftKayBoard(boolean keyboardVisible) {
        if (keyboardVisible)
            scrollBot();
    }

    ActId act;

    private void parseExtras(Bundle extras) {
        if (extras == null)
            return;
        if (extras.containsKey("name"))
            creator_name = extras.getString("name");
        if (extras.containsKey("id"))
            user_ids.add(extras.getString("id"));
        if (extras.containsKey(ActId.TAG)) {
            adapter.isGeneral();
            act = (ActId) extras.getSerializable(ActId.TAG);
            presenter.loadUsers((ActId) extras.getSerializable(ActId.TAG));
        } else if (extras.containsKey(ID)) {
            presenter.loadUser(getIntent().getStringExtra(ID));
        }
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        setBackArrow(bar);
    }

    @Override
    protected int menuLayout() {
        return R.menu.dialog_menu;
    }

    @Override
    protected void onCreateMenu(Menu menu) {
        super.onCreateMenu(menu);
        ava = menu.findItem(R.id.dialog_avatar).getActionView().findViewById(R.id.avatar);
        ava.setOnClickListener(v -> presenter.openUsers());
        ava1 = menu.findItem(R.id.dialog_avatar).getActionView().findViewById(R.id.avatar1);
        ava1.setOnClickListener(v -> presenter.openUsers());
        ava2 = menu.findItem(R.id.dialog_avatar).getActionView().findViewById(R.id.avatar2);
        ava2.setOnClickListener(v -> presenter.openUsers());
        parseExtras(getIntent().getExtras());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.dialog_avatar) {
            presenter.openUsers();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openUser(String id) {
        EmptyActivity.startActivityCabinet(this, id);
    }

    @Override
    public void openUsers(ActId actId, boolean c) {
        ParticipantsActivity.open(this, actId, c);
    }

    @Override
    public void connected() {
        inputPanel.setVisibility(View.VISIBLE);
    }

    @Override
    public void disconnected() {
        inputPanel.setVisibility(View.GONE);
        error();
    }

    @Override
    public void permissionDenied() {
        DialogProvider.infoOk(this, R.string.permissions_denied).show();
    }

    @Override
    public void notCanWrite() {
        DialogProvider.infoOk(this, R.string.you_can_not_write, this::finish).show();
    }

    @Override
    public void error() {
        DialogProvider.infoOk(this, "Ошибка, попробуйте еще раз", this::finish).show();
    }

    @OnClick(R.id.dialog_attach_button)
    public void onDialogAttachButtonClicked() {
        DialogProvider.listPhoto(this,
                new String[]{
                        getString(R.string.load_from_gallery),
                        getString(R.string.take_picture)},
                this::takeFromGallery,
                this::takePicture
        ).show();
    }

    @OnClick(R.id.dialog_send_button)
    public void onSendMessage() {
        SocketChat socketChat = SocketChat.getInstance();

        JSONObject chObj = new JSONObject();
        try {
            chObj.put("roomId", room_id);
            chObj.put("text", inputPanelHolder.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (socketChat.getChat() != null) {
            socketChat.getChat().emit("message", chObj);
        }
        inputPanelHolder.claerMessage();
    }

    private void takeFromGallery() {
        takeAndSendPhoto(OpenPhotoActivity.open(this, true));
    }

    private void takePicture() {
        takeAndSendPhoto(OpenPhotoActivity.open(this, false));
    }

    private void takeAndSendPhoto(Maybe<Uri> maybe) {
        maybe
                .map(this::newImageMessage)
                .subscribe(this::sendImageMessage,
                        throwable -> {
                            DialogProvider.infoOk(this, throwable.getMessage()).show();
                            throwable.printStackTrace();
                        });
    }

    private void sendImageMessage(SockMessage m) {
        String photo = getFileToByte(RealPathUtil.getRealPath(this, Uri.parse(m.photo)));
        SocketChat socketChat = SocketChat.getInstance();

        JSONObject chObj = new JSONObject();
        try {
            chObj.put("roomId", room_id);
            chObj.put("photo", photo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (socketChat.getChat() != null) {
            socketChat.getChat().emit("message", chObj);
        }

    }

    public static String getFileToByte(String filePath) {
        Bitmap bmp = null;
        ByteArrayOutputStream bos = null;
        byte[] bt = null;
        String encodeString = null;
        try {
            bmp = BitmapFactory.decodeFile(filePath);
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bt = bos.toByteArray();
            encodeString = Base64.encodeToString(bt, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "data:image/jpeg;base64," + encodeString;
    }

    private SockMessage newImageMessage(Uri uri) {
        SockMessage m = new SockMessage(String.valueOf(uri));
        return m;
    }

    /*
        @Override
        public void loadMessage(List<SockMessage> messages) {
            hideLoad();
            if (users!=null)
            for(int i=0;i<messages.size();i++)
            {

                for (User user:users)
                {
                    if(messages.get(i).user_id.equals(user.id()))
                    {
                          messages.get(i).name = user.firstname;
                    }
                }

            }
    //        for(int i=0;i<messages.size();i++) {
    //            messages.get(i).status = Message.Status.READ;
    //        }

            adapter.setMessages(messages);
            scrollBot();
            setReadMessages(messages);
        }
    */
    private void scrollBot() {
        if (dialogMessageList != null)
            dialogMessageList.scrollToPosition(dialogMessageList.getAdapter().getItemCount() - 1);
    }


    @Override
    public void loadUser(User user) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(user.firstname);
            ImageLoader.loadImage(user.imageUrl, ava);
            TabsActivity.removeNotyCount(user.id());
            user_ids.add(user.id());
        }
    }

    @Override
    public void setTitle(String eventName) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(eventName);
    }

    @Override
    public void loadUsers(List<User> chatUsers) {
        users = chatUsers;
        for (User chat : chatUsers)
            user_ids.add(chat.id());
        if (getSupportActionBar() != null) {
            if (chatUsers.size() > 0)
                ImageLoader.loadImage(chatUsers.get(0).imageUrl, ava);
            if (chatUsers.size() > 1 && ava1 != null) {
                ImageLoader.loadImage(chatUsers.get(1).imageUrl, ava1);
                ava1.setVisibility(View.VISIBLE);
            }
            if (chatUsers.size() > 2 && ava2 != null) {
                ImageLoader.loadImage(chatUsers.get(2).imageUrl, ava2);
                ava2.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void newMessage(SockMessage message) {

        adapter.newMessage(message);
        scrollBot();
    }

    @Override
    public void updateMessage(SockMessage message) {
        adapter.update(message);
    }

    @Override
    public void record(int time) {
        Toaster.duration(this, String.valueOf(time) + " sec", 100);
    }

    @Override
    public void play(int time, int maxTime) {
//        if(presenter.isPlaying())
//            presenter.stopPlay();
//        adapter.setProgressPlay(time, maxTime);
//        if (time==maxTime)
//            new Handler().postDelayed(() -> adapter.setProgressPlay(-1, 0),maxTime*1000);

    }

    @Override
    public void write(String id) {
        if (user_ids.contains(id)) {
            adapter.write(id);
            scrollBot();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.stopPlay();
    }

    @Override
    public void showInfo(int messageId, Object... params) {
        if (messageId != 0)
            DialogProvider.infoOk(this, getString(messageId, params), this::finish).show();
        else DialogProvider.infoOk(this, String.valueOf(params[0]), this::finish).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SocketChat socketChat = SocketChat.getInstance();
        if (socketChat.getChat() != null) {
            JSONObject chObj = new JSONObject();

            try {
                chObj.put("roomId", room_id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            socketChat.getChat().emit("leaveFromRoom", chObj);
        }
    }
}
