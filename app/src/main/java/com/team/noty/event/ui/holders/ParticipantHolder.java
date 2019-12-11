package com.team.noty.event.ui.holders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.team.noty.event.R;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.ui.activities.DialogActivity;
import com.team.noty.event.ui.activities.EmptyActivity;
import com.team.noty.event.utils.ImageLoader;
import com.team.noty.event.utils.RateCalc;
import com.team.noty.event.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class ParticipantHolder implements Unbinder {

    @BindView(R.id.part_photo)
    CircleImageView photo;
    @BindView(R.id.part_name_age)
    TextView name_age;
    @BindView(R.id.part_city)
    TextView city;
    @BindView(R.id.part_organizer_rank)
    TextView rate;
    @BindView(R.id.part_send)
    View sendBtn;
    @BindView(R.id.part_check)
    View checkView;
    private Unbinder unbinder;
    private boolean isChecked;
    private String userId;

    public ParticipantHolder(View view) {
        unbinder = ButterKnife.bind(this, view);
        setClicks(view, sendBtn);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ParticipantHolder(View view, String name, String age, String photoUrl, String city, int rate) {
        this(view);
        setCity(city);
        setName_age(name, age);
        setPhoto(photoUrl);
        setRate(rate);
        check(false);
        if (userId != null && userId.equals(AuthData.getID()))
            hideButton();
    }

    private void setClicks(View root, View sendBtn) {
        sendBtn.setOnClickListener(v -> DialogActivity.openDialog(v.getContext(), userId));
        root.setOnClickListener(v -> EmptyActivity.startActivityCabinet(v.getContext(), userId));
    }

    public void check(boolean check) {
        if (check)
            checkView.setVisibility(View.VISIBLE);
        else checkView.setVisibility(View.GONE);
    }

    public void hideButton() {
        if (sendBtn != null)
            sendBtn.setVisibility(View.GONE);
    }

    public void showButton() {
        if (sendBtn != null)
            sendBtn.setVisibility(View.VISIBLE);
    }

    public void setPhoto(String photo) {
        ImageLoader.loadImage(photo, this.photo);
    }

    public void setName_age(String name, String age) {
        name_age.setText(name_age.getContext().getString(R.string.user_name_age, name, age));
    }

    public void setCity(String city) {
        if (city == null || city.isEmpty())
            this.city.setVisibility(View.GONE);
        else {
            this.city.setVisibility(View.VISIBLE);
            this.city.setText(city);
        }
    }

    public void setRate(int level) {
        setRateText(RateCalc.getRate(this.rate.getContext(), level));
        setRateColor(level);
    }

    public void setRateText(String rateName) {
        this.rate.setText(rateName);
    }

    public void setRateColor(int level) {
        this.photo.setBorderColor(RateCalc.getColor(this.rate.getContext(), level));
    }

    @Override
    public void unbind() {
        unbinder.unbind();
    }
}
