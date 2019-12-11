package com.team.noty.event.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team.noty.event.R;
import com.team.noty.event.app.Injector;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.presenters.EventPresenter;
import com.team.noty.event.mvp.views.EventView;
import com.team.noty.event.ui.adapters.BaseMenuAdapter;
import com.team.noty.event.ui.adapters.EventEnterPeopleAdapter;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.ui.dialogs.PopupBot;
import com.team.noty.event.ui.holders.ParticipantHolder;
import com.team.noty.event.ui.models.UiAct;
import com.team.noty.event.ui.models.UiMenuItem;
import com.team.noty.event.ui.models.UiUser;
import com.team.noty.event.ui.utils.LinkUtils;
import com.team.noty.event.ui.utils.RecyclerUtils;
import com.team.noty.event.utils.AccountPreferenceManager;
import com.team.noty.event.utils.DateFormatter;
import com.team.noty.event.utils.ImageLoader;
import com.team.noty.event.utils.OnMapAndViewReadyListener;
import com.team.noty.event.utils.callbacks.CallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("SetTextI18n")
public class EventActivity extends BaseActivity implements EventView {

    @InjectPresenter
    EventPresenter presenter;

    @BindView(R.id.root)
    View root;
    @BindView(R.id.event_youtube)
    ImageView eventYoutube;

    private GoogleMap map;
    private LatLng location;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.event_organizer_title)
    View titleCreator;
    @BindView(R.id.event_creator_separator)
    View titleSep;

    @BindView(R.id.event_date)
    TextView eventDate;
    @BindView(R.id.event_address)
    TextView eventAddress;
    @BindView(R.id.event_price)
    TextView eventPrice;
    @BindView(R.id.event_count)
    TextView eventCount;
    @BindView(R.id.event_description)
    TextView eventDescription;
    @BindView(R.id.event_parts)
    RecyclerView eventParts;
    @BindView(R.id.event_image)
    ImageView eventImage;
    @BindView(R.id.event_title)
    TextView eventTitle;
    @BindView(R.id.event_go_btn)
    Button goBtn;
    @BindView(R.id.event_creator)
    View eventCreatorView;
    @BindView(R.id.event_chat)
    View chat;
    @BindView(R.id.event_parts_title)
    View partsTitle;
    BaseMenuAdapter adapterMenu;
    ParticipantHolder creator;
    private EventEnterPeopleAdapter adapter;
    private Intent tabsIntent,regIntent;
    public User user_creator = null;

    public static void open(Context context, ActId actId) {
        if (!Injector.get().getApp().accountPreferenceManager().checkValue(AccountPreferenceManager.TOKEN))
            DialogProvider.needReg(context, () -> RegistrationActivity.open(context)).show();
        else
            context.startActivity(intent(context, actId));
    }

    public static Intent intent(Context context, ActId actId) {
        return new Intent(context, EventActivity.class).putExtra(ActId.TAG, actId);
    }

    @Override
    protected int layout() {
        return R.layout.activity_event;
    }
    String data,type;
    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        adapterMenu = new BaseMenuAdapter();
        showLoad();
        actCreator();
        root.setVisibility(View.INVISIBLE);
        setSupportActionBar(toolbar);
        tabsIntent = new Intent(this,TabsActivity.class);
        tabsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        regIntent = new Intent(this,RegistrationActivity.class);
        regIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent intent = getIntent();
        try {
            data = intent.getData().getQueryParameter("id");
            type =  intent.getData().getEncodedPath();
        }catch (Exception e){}
        mapOption((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_lite));
        adapter = new EventEnterPeopleAdapter(new ArrayList<>());
        adapter.setCallBack(this::openProfile);
        RecyclerUtils.setHorisontalAdapter(eventParts, adapter);
        creator = new ParticipantHolder(eventCreatorView);
        creator.hideButton();
        goBtn.setOnClickListener(view -> presenter.goCall());
        if(data!=null) {
            if (!Injector.get().getApp().accountPreferenceManager().checkValue(AccountPreferenceManager.TOKEN)) {
                DialogProvider.needReg(this, () -> startActivity(regIntent), this::finish).show();
            }
            else
                presenter.getAct(new ActId(data, type.equals("/events")));
        }
        else
            if (getIntent().getExtras() != null)
                loadEvent(getIntent().getExtras());
    }

    private void mapOption(SupportMapFragment mapFragment) {
        if (mapFragment != null)
            new OnMapAndViewReadyListener(mapFragment, this::onMapReady);
    }

    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(false);
        setMarker(location, googleMap);
    }

    private void setMarker(LatLng location, GoogleMap map) {
        this.location = location;
        this.map = map;
        if (this.location != null && map != null) {
            map.addMarker(new MarkerOptions()
                    .position(this.location)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_event)));
            map.moveCamera(CameraUpdateFactory.newLatLng(this.location));
        }
    }

    private void loadEvent(Bundle b) {
        ActId actId = null;
        if (b.containsKey(ActId.TAG))
            actId = (ActId) b.getSerializable(ActId.TAG);
        presenter.getAct(actId);
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        setBackArrow(bar);
        bar.setTitle("");
        bar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    @Override
    public void showAct(UiAct uiAct) {
        hideLoad();
        root.setVisibility(View.VISIBLE);
        setMarker(new LatLng(uiAct.act.latitude, uiAct.act.longitude), map);
        ImageLoader.loadImage(uiAct.act.imageUrl, eventImage);
        eventTitle.setText(uiAct.act.title);
        eventAddress.setText(uiAct.act.address);
        eventDate.setText(uiAct.formatedDate);
        eventPrice.setText(getPrice(uiAct.act.price));
        eventCount.setText(uiAct.getCount());
        if (uiAct.act.description==null||uiAct.act.description.equals("Описание отсутствует"))
            eventDescription.setVisibility(View.GONE);
        eventDescription.setText(uiAct.act.description);
        if (uiAct.act.video != null && !uiAct.act.video.isEmpty() && !uiAct.act.video.equals("null")) {
            ImageLoader.loadYouTubeThamb(uiAct.act.video, eventImage);
            eventYoutube.setVisibility(View.VISIBLE);
            eventYoutube.setTag(uiAct.act.video);
            eventYoutube.setOnClickListener(this::watchYoutubeVideo);
        }
    }

    private String getPrice(int price) {
        if (price == 0)
            return getString(R.string.free);
        else return getString(R.string.currency_template, price);
    }

    public void watchYoutubeVideo(View view) {
        VideoActivity.open(this, String.valueOf(view.getTag()));
    }

    @Override
    public void showCreator(UiUser user) {
        user_creator = user.user;
        creator.setName_age(user.user.firstname,
                String.valueOf(DateFormatter.getAge(user.user.date)));
        creator.setPhoto(user.user.imageUrl);
        creator.setCity(user.user.city);
        creator.setRate(user.user.level);
        creator.setUserId(String.valueOf(user.user.id));
        if (user.canWrite)
            creator.showButton();
        else creator.hideButton();
        actVisitor();
    }

    public void actVisitor() {
        eventCreatorView.setVisibility(View.VISIBLE);
        titleCreator.setVisibility(View.VISIBLE);
        titleSep.setVisibility(View.VISIBLE);
        goBtn.setVisibility(View.VISIBLE);
        adapterMenu.setItems(createMenuList());
    }

    private void actCreator() {
        eventCreatorView.setVisibility(View.GONE);
        titleCreator.setVisibility(View.GONE);
        titleSep.setVisibility(View.GONE);
        goBtn.setVisibility(View.GONE);
        adapterMenu.setItems(createMenuListCreator());
    }

    @Override
    protected int menuLayout() {
        return R.menu.triple_point_menu;
    }

    @Override
    public void onBackPressed() {
        if(data!=null)
            startActivity(tabsIntent);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.button) {
            PopupBot.show(adapterMenu, getSupportFragmentManager(), PopupBot.Gravity.BOT);
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<UiMenuItem> createMenuList() {
        List<UiMenuItem> items = new ArrayList<>();
        items.add(new UiMenuItem(getResources().getStringArray(
                R.array.event_menu)[0],
                R.drawable.ic_share,
                presenter::share));
        items.add(new UiMenuItem(getResources().getStringArray(
                R.array.event_menu)[1],
                R.drawable.ic_link,
                presenter::copyLink));
        items.add(new UiMenuItem(getResources().getStringArray(
                R.array.event_menu)[2],
                R.drawable.ic_info,
                presenter::openComplaint));
        return items;
    }

    @Override
    public void copyLink(String link) {
        LinkUtils.copy(this, link);
    }

    @Override
    public void shareLink(String link) {
        LinkUtils.share(this, link);
    }

    private List<UiMenuItem> createMenuListCreator() {
        List<UiMenuItem> items = new ArrayList<>();
        items.add(new UiMenuItem(getResources().getStringArray(R.array.event_menu_creator)[0],
                R.drawable.ic_share,
                presenter::share));
        items.add(new UiMenuItem(getResources().getStringArray(R.array.event_menu_creator)[1],
                R.drawable.ic_link,
                presenter::copyLink));
        items.add(new UiMenuItem(getResources().getStringArray(R.array.event_menu_creator)[2],
                R.drawable.ic_edit_1, presenter::edit));
        items.add(new UiMenuItem(getResources().getStringArray(R.array.event_menu_creator)[3],
                R.drawable.ic_delete,
                presenter::deleteAct));
        return items;
    }

    @Override
    public void openEdit(ActId actId) {
        if (actId.isEvent)
            EmptyBarActivity.startEditEvent(this, actId.id);
        else EmptyBarActivity.startEditComm(this, actId.id);
    }

    @Override
    public void showParts(List<User> users) {
        adapter.setList(users);
    }

    @OnClick(R.id.event_go_btn)
    public void onEventGoBtnClicked() {
        presenter.goCall();
        goBtn.setEnabled(false);
    }

    @Override
    public void dismissGoBtn() {
        goBtn.setText(R.string.canceled);
        goBtn.setEnabled(false);
        setBlueGoBtn();
        chat.setVisibility(View.GONE);
    }

    @Override
    public void confirmGoBtn() {
        goBtn.setText(R.string.cancel);
        setRedGoBtn();
        goBtn.setEnabled(true);
        chat.setVisibility(View.VISIBLE);
    }

    @Override
    public void falseGoBtn() {
        goBtn.setText(R.string.i_go);
        setBlueGoBtn();
        goBtn.setEnabled(true);
        chat.setVisibility(View.GONE);
    }

    @Override
    public void requestGoBtn() {
        goBtn.setText(R.string.moderating);
        setRedGoBtn();
        //goBtn.setEnabled(false);
        chat.setVisibility(View.GONE);
    }

    @Override
    public void passedGoBtn(boolean imJoin) {
        goBtn.setText(imJoin ? R.string.give_feedback : R.string.passed);
        setBlueGoBtn();
        goBtn.setEnabled(imJoin);
        chat.setVisibility(View.GONE);
    }

    @Override
    public void noPlacesGoBtn() {
        goBtn.setText(R.string.no_places);
        setBlueGoBtn();
        goBtn.setEnabled(false);
        chat.setVisibility(View.GONE);
    }

    private void setRedGoBtn() {
        goBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.button_red_selector));
    }

    private void setBlueGoBtn() {
        goBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.button_selector));
    }

    @Override
    public void openDialog(String user_id) {
        DialogActivity.openDialog(this, user_id);
    }

    @OnClick(R.id.event_party_btn)
    public void onEventPartyBtnClicked() {
        ParticipantsActivity.open(this,
                new ActId(presenter.getAct().id(), presenter.getAct().isEvent),
                presenter.imCreator());
    }

    @Override
    public void openProfile(String user_id) {
        EmptyActivity.startActivityCabinet(this, user_id);
    }

    @OnClick(R.id.event_chat)
    public void onEventChatClicked() {
        presenter.openChat();
    }

    @Override
    public void openChat(ActId actId) {
        if (user_creator!=null) {
            String s = user_creator.firstname + " " + user_creator.lastname;
            DialogActivity.openChat(this, actId,s,user_creator.id() );
        }
        else
            DialogActivity.openChat(this, actId,null,null );
    }

    @Override
    public void openComplaint(ActId actId) {
        if (actId.isEvent)
            ComplaintActivity.openFromEvent(this, actId.id);
        else ComplaintActivity.openFromComm(this, actId.id);
    }

    @Override
    public void openDelete(ActId actId) {
        if (actId.isEvent)
            ComplaintActivity.openFromDeleteEvent(this, actId.id);
        else ComplaintActivity.openFromDeleteComm(this, actId.id);
    }

    @Override
    public void ageException() {
        runOnUiThread(() -> DialogProvider.infoOk(EventActivity.this, R.string.age_limit_exception, () -> finish()).show());

    }

    @Override
    public void showMessageButtonCreator() {
        if (creator != null)
            creator.showButton();
    }

    @Override
    public void hideUserList() {
        partsTitle.setVisibility(View.GONE);
    }

    @Override
    public void showInfo(int messageId, Object... params) {
        if (params.length > 0 && String.valueOf(params[0]).isEmpty())
            super.showInfo(messageId, params);
    }

    @Override
    public void close(String error) {
        DialogProvider.infoOk(this, error, this::finish).show();
    }

    @Override
    public void openFeedBack(String id) {
        GiveFeedbackActivity.open(this, id);
        finish();
    }

    @Override
    public void blocked() {
        DialogProvider.infoOk(this, R.string.event_comm_blocked, this::finish).show();
    }
}
