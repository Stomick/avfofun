package com.team.noty.event.ui.fragments.main;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.team.noty.event.R;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.Comment;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.presenters.CabinetPresenter;
import com.team.noty.event.mvp.views.tabs.CabinetTabView;
import com.team.noty.event.ui.activities.ComplaintActivity;
import com.team.noty.event.ui.activities.DialogActivity;
import com.team.noty.event.ui.activities.EditCategoriesActivity;
import com.team.noty.event.ui.activities.EditProfileActivity;
import com.team.noty.event.ui.activities.EmptyActivity;
import com.team.noty.event.ui.activities.OpenPhotoActivity;
import com.team.noty.event.ui.activities.PhotoViewActivity;
import com.team.noty.event.ui.activities.ReviewActivity;
import com.team.noty.event.ui.adapters.BaseMenuAdapter;
import com.team.noty.event.ui.adapters.CommentsAdapter;
import com.team.noty.event.ui.adapters.EventAdapterProfile;
import com.team.noty.event.ui.adapters.SocAdapter;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.ui.dialogs.PopupBot;
import com.team.noty.event.ui.fragments.BaseFragment;
import com.team.noty.event.ui.models.UiMenuItem;
import com.team.noty.event.ui.models.UiSoc;
import com.team.noty.event.ui.utils.LinkUtils;
import com.team.noty.event.ui.utils.RecyclerUtils;
import com.team.noty.event.utils.DateFormatter;
import com.team.noty.event.utils.ImageLoader;
import com.team.noty.event.utils.RateCalc;
import com.team.noty.event.utils.RealPathUtil;
import com.team.noty.event.utils.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;

@SuppressLint("CheckResult")
public class CabinetTabFragment extends BaseFragment implements CabinetTabView {

    public static final String TAG = CabinetTabFragment.class.getSimpleName();

    @InjectPresenter
    CabinetPresenter presenter;

    @BindView(R.id.cabinet_photo)
    public ImageView cabinetPhoto;
    @BindView(R.id.cabinet_rank)
    TextView cabinetRank;
    @BindView(R.id.cabinet_progress)
    ProgressBar cabinetProgress;
    @BindView(R.id.cabinet_name)
    TextView cabinetName;
    @BindView(R.id.cabinet_timer)
    TextView cabinetTimer;
    @BindView(R.id.cabinet_created_event)
    TextView cabinetCreatedEvent;
    @BindView(R.id.cabinet_visits_event)
    TextView cabinetVisitsEvent;
    @BindView(R.id.cabinet_age)
    TextView cabinetAge;
    @BindView(R.id.cabinet_city)
    TextView cabinetCity;
    @BindView(R.id.cabinet_social)
    RecyclerView cabinetSocial;
    @BindView(R.id.cabinet_phone)
    TextView cabinetPhone;
    @BindView(R.id.cabinet_mail)
    TextView cabinetMail;
    @BindView(R.id.cabinet_about_me)
    TextView cabinetAboutMe;
    @BindView(R.id.cabinet_my_events)
    RecyclerView cabinetMyEvents;
    @BindView(R.id.cabinet_my_community)
    RecyclerView cabinetMyCommunity;
    @BindView(R.id.cabinet_my_comments)
    RecyclerView cabinetMyComments;
    @BindView(R.id.cabinet_interests)
    View cabinetInterests;

    EventAdapterProfile eventAdapterProfile;
    EventAdapterProfile commAdapterProfile;
    CommentsAdapter commentsAdapter;
    @BindView(R.id.cabinet_send_message)
    Button cabinetSendMessage;
    @BindView(R.id.under_phone)
    View underPhone;
    @BindView(R.id.under_mail)
    View underMail;
    @BindView(R.id.under_interests)
    View underInterests;
    @BindView(R.id.cabinet_add_friend)
    ImageView barAddFriend;
    @BindView(R.id.cabinetcabinet_title)
    TextView barCabinetTitle;
    @BindView(R.id.cabinet_edit)
    ImageView barEdit;

    BaseMenuAdapter menuAdapter;
    @BindView(R.id.cabinet_my_event)
    TextView cabinetMyEvent;
    @BindView(R.id.cabinet_my_event_look)
    TextView cabinetMyEventLook;
    @BindView(R.id.cabinet_my_communities)
    TextView cabinetMyCommunities;
    @BindView(R.id.cabinet_my_communities_look)
    TextView cabinetMyCommunitiesLook;
    @BindView(R.id.cabinet_my_comments_title)
    TextView cabinetMyCommentsTitle;
    @BindView(R.id.cabinet_my_comments_look)
    TextView cabinetMyCommentsLook;

    private SocAdapter socAdapter;
    private String user_id;
    public static String about = null;
    public static User user_static = null;
    public static String phoneNumber = null;
    public static String photoPath = null;
    public boolean isMan = true;

    public static CabinetTabFragment createVisitor(String id_user) {
        CabinetTabFragment fragment = new CabinetTabFragment();
        fragment.user_id = id_user;
        return fragment;
    }

    static {
        user_static = new User();
    }

    private boolean isVisit() {
        return user_id != null && !user_id.equals(AuthData.getID());
    }

    @Override
    protected int layout() {
        return R.layout.fragment_cabinet;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        eventAdapterProfile = new EventAdapterProfile();
        commAdapterProfile = new EventAdapterProfile();
        commentsAdapter = new CommentsAdapter();
        RecyclerUtils.setHorisontalAdapter(cabinetMyEvents, eventAdapterProfile);
        RecyclerUtils.setHorisontalAdapter(cabinetMyCommunity, commAdapterProfile);
        RecyclerUtils.setHorisontalAdapter(cabinetMyComments, commentsAdapter);
        socAdapter = SocAdapter.showable();
        RecyclerUtils.setVerticalAdapter(cabinetSocial, socAdapter);
    }

    private void setup() {
        if (isVisit()) {

            cabinetPhone.setVisibility(View.GONE);
            cabinetMail.setVisibility(View.GONE);
            cabinetInterests.setVisibility(View.GONE);
            underInterests.setVisibility(View.GONE);
            underMail.setVisibility(View.GONE);
            underPhone.setVisibility(View.GONE);
            menuAdapter = new BaseMenuAdapter();
            menuAdapter.setItems(createMenuList());
            presenter.readUser(user_id);
        } else
            presenter.readUser();
        setLeftButton();
        setRightButton();
        loadIndicator.showLoad();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getView() != null)
            getView().setVisibility(View.GONE);
        setup();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setLeftButton() {
        if (isVisit()) {
            barAddFriend.setImageResource(R.drawable.ic_arrow_back);
            barAddFriend.setVisibility(View.VISIBLE);
        }
        barAddFriend.setOnClickListener(v -> leftButtonBarClick());
    }

    private void setRightButton() {
        if (isVisit())
            barEdit.setImageResource(R.drawable.ic_triple_point);
        barEdit.setOnClickListener(v -> rightButtonBarClick());
    }

    private void leftButtonBarClick() {
        if (isVisit()) {
            if (getActivity() != null) getActivity().onBackPressed();
        } else {
            ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Events", getString(R.string.link));
            clipboard.setPrimaryClip(clip);
            Snackbar.make(getView(), R.string.copied_to_clipboard, Snackbar.LENGTH_LONG).show();
        }
    }

    private void rightButtonBarClick() {
        if (isVisit()) {
            PopupBot.show(menuAdapter, getChildFragmentManager(), PopupBot.Gravity.BOT);
        } else
            startActivity(new Intent(getContext(), EditProfileActivity.class));
    }

    private List<UiMenuItem> createMenuList() {
        List<UiMenuItem> items = new ArrayList<>();
        items.add(new UiMenuItem(getResources().getStringArray(R.array.profile_menu)[0], R.drawable.ic_share, this::shareLink));
        items.add(new UiMenuItem(getResources().getStringArray(R.array.profile_menu)[1], R.drawable.ic_link, this::copyLink));
        items.add(new UiMenuItem(getResources().getStringArray(R.array.profile_menu)[2], R.drawable.ic_info, this::openComplaint));
        return items;
    }

    protected void copyLink() {
        if (getContext() != null)
            LinkUtils.copy(getContext(), presenter.createLink());
    }

    private void shareLink() {
        if (getContext() != null)
            LinkUtils.share(getContext(), presenter.createLink());
    }

    private void openComplaint() {
        if (getContext() != null)
            ComplaintActivity.openFromUser(getContext(), user_id);
    }

    @OnClick(R.id.cabinet_photo)
    public void onSetPhoto() {
        if (!isVisit())
            DialogProvider.avatarOptions(getContext(),
                    () -> loadPhoto(true),
                    () -> loadPhoto(false),
                    this::open,
                    this::delete
            ).show();
    }

    private void loadPhoto(boolean gallery) {
        OpenPhotoActivity.open(getActivity(), gallery, OpenPhotoActivity.AspectFor.AVATAR)
                .map(uri -> RealPathUtil.getRealPath(getContext(), uri))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(path -> presenter.editPhoto(path));
    }

    private void open() {
        presenter.openPhoto();
    }

    private void delete() {
        presenter.deletePhoto();
    }

    @Override
    public void openPhoto(String url) {
        if (getContext() != null)
            PhotoViewActivity.openImage(getContext(), url);
    }

    public String getDayType(long days) {
        int dayType = (int) (days % 10);
        if (dayType == 1)
            return " день";
        if (dayType > 1 && dayType < 5)
            return " дня";
        if (dayType > 4)
            return " дней";
        return " дней";
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showUser(User user) {
        if (user != null) {
            try {
                if (user.gender != null)
                    isMan = !user.gender.toLowerCase().equals("женский");
                cabinetName.setText(String.valueOf(user_static.firstname != null ? user_static.firstname : user.firstname) + " " + String.valueOf(user_static.lastname != null ? user_static.lastname : user.lastname));
                cabinetAge.setText(getString(R.string.age_t, String.valueOf(DateFormatter.getAge(user.date))));

                if (about != null)
                    cabinetAboutMe.setText(StringUtils.emptyIfNull(about));
                else
                    cabinetAboutMe.setText(StringUtils.emptyIfNull(user.info));
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse(user.reg);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long millis = date.getTime();
                if (date != null) {
                    long dif = System.currentTimeMillis() - millis;
                    cabinetTimer.setText("На шкипере уже " + TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS) + getDayType(TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS)));
                } else
                    cabinetTimer.setText(String.valueOf(user.reg));
                cabinetMail.setText(getString(R.string.email_t, StringUtils.emptyIfNull(user.email)));
                cabinetCity.setText(String.valueOf(user.city));
                if (user.phone != null)
                    if (phoneNumber != null)
                        cabinetPhone.setText(phoneCorrect(phoneNumber));
                    else
                        cabinetPhone.setText(phoneCorrect(user.phone));
                else cabinetPhone.setVisibility(View.GONE);
                if (getContext() != null)
                    cabinetRank.setText(RateCalc.getRate(getContext(), user.level));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    cabinetProgress.setProgressTintList(ColorStateList.valueOf(RateCalc.getColor(getContext(), user.level)));
                }
                if (photoPath != null && !isVisit())
                    cabinetPhoto.setImageURI(Uri.parse(photoPath));
                else
                    ImageLoader.loadImage(user.imageUrl, cabinetPhoto, R.drawable.ava_cabinet);
                if (getView() != null)
                    getView().setVisibility(View.VISIBLE);
                loadIndicator.hideLoad();
                int a = RateCalc.getRating(user.level);
                cabinetProgress.setMax(RateCalc.getRating(user.level));
                cabinetProgress.setProgress(user.rating);
                checkVisit(user);
                cabinetVisitsEvent.setText(getString(R.string.visit_t, String.valueOf(user.visited)));
                cabinetCreatedEvent.setText(getString(R.string.create_t, String.valueOf(user.created)));
            } catch (Exception e) {
                Log.e("my", e.getMessage());
            }
        }
    }

    private String phoneCorrect(String phone) {
        if (!phone.contains("+"))
            phone = "+" + phone;
        return getString(R.string.phone_t, StringUtils.emptyIfNull(phone));
    }

    private void checkVisit(User user) {
        if (isVisit()) {
            barCabinetTitle.setText(user.firstname);
            cabinetVisitsEvent.setText(getString(R.string.visit_t, String.valueOf(user.visited)));
            cabinetCreatedEvent.setText(getString(R.string.create_t, String.valueOf(user.created)));
//            if (user.commAccess == 0 && user.eventAccess == 0) {
//                cabinetVisitsEvent.setVisibility(View.INVISIBLE);
//                cabinetCreatedEvent.setVisibility(View.INVISIBLE);
//            }
        }
        if (!isVisit()) {
            cabinetVisitsEvent.setVisibility(View.VISIBLE);
            cabinetCreatedEvent.setVisibility(View.VISIBLE);
            if (getFragmentManager() != null)
                cabinetAboutMe.setOnClickListener(v -> DialogProvider.editText(
                        getString(R.string.about_me),
                        this::editAbout,
                        cabinetAboutMe.getText().toString()).show(getFragmentManager(), "write"));
            View.OnClickListener openEdit = v -> startActivity(new Intent(getContext(), EditProfileActivity.class));
            cabinetPhone.setOnClickListener(openEdit);
            cabinetMail.setOnClickListener(openEdit);
        }
    }

    private void editAbout(String text) {
        if (text == null || text.equals(cabinetAboutMe.getText().toString()))
            return;
        cabinetAboutMe.setText(text);
        about = text;
        presenter.setAbout(text);
        showLoad();
    }

    @Override
    public void showInfo(int messageId, Object... objects) {
        super.showInfo(messageId, objects);
        hideLoad();
    }

    public void showEvents(List<Act> events) {
        if (events.isEmpty()) {
            cabinetMyEvent.setVisibility(View.GONE);
            cabinetMyEventLook.setVisibility(View.GONE);
        } else {
            eventAdapterProfile.setList(events);
            cabinetMyEvent.setVisibility(View.VISIBLE);
            cabinetMyEventLook.setVisibility(View.VISIBLE);
            if (isVisit()) {

                cabinetMyEvent.setText(isMan ? "Его события" : "Её события");
            }
        }
    }

    public void showCommunities(List<Act> events) {
        if (events.isEmpty()) {
            cabinetMyCommunities.setVisibility(View.GONE);
            cabinetMyCommentsLook.setVisibility(View.GONE);
        } else {
            commAdapterProfile.setList(events);
            cabinetMyCommunities.setVisibility(View.VISIBLE);
            cabinetMyCommunitiesLook.setVisibility(View.VISIBLE);
            if (isVisit()) {

                cabinetMyCommunities.setText(isMan ? "Его сообщества" : "Её сообщества");
            }
        }
    }

    @Override
    public void showComments(List<Comment> comments) {
        if (comments.isEmpty()) {
            cabinetMyCommentsLook.setVisibility(View.GONE);
            cabinetMyComments.setVisibility(View.GONE);
        } else {
            commentsAdapter.setComments(comments);
            cabinetMyCommentsLook.setVisibility(View.VISIBLE);
            cabinetMyComments.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.cabinet_interests)
    public void onViewClicked() {
        if (getContext() != null)
            EditCategoriesActivity.open(getContext());
    }

    @OnClick(R.id.cabinet_send_message)
    public void onSendMessage() {
        presenter.openDialog();
    }

    @Override
    public void openDialog(String user_id) {
        if (getContext() != null)
            DialogActivity.openDialog(getContext(), user_id);
    }

    @Override
    public void loadSoc(List<UiSoc> socs) {
        socAdapter.load(socs);
        if (!socAdapter.getSocList().isEmpty())
            cabinetSocial.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSendButton() {
        cabinetSendMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void changePhoto(String s) {
        Glide.with(getView()).load(s).into(cabinetPhoto);
    }

    @OnClick({R.id.cabinet_my_event_look, R.id.cabinet_my_communities_look, R.id.cabinet_my_comments_look})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cabinet_my_event_look:
                if (getContext() != null)
                    EmptyActivity.startActivityEventsProfile(getContext(), user_id);
                break;
            case R.id.cabinet_my_communities_look:
                if (getContext() != null)
                    EmptyActivity.startActivityCommProfile(getContext(), user_id);
                break;
            case R.id.cabinet_my_comments_look:
                Intent intent = new Intent(getContext(), ReviewActivity.class);
                intent.putExtra("is_my", true);
                intent.putExtra("user_id", presenter.current.id());
                startActivity(intent);
                break;
        }
    }
}
