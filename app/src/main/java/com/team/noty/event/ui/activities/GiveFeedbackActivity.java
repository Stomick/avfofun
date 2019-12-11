package com.team.noty.event.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.team.noty.event.R;
import com.team.noty.event.models.FeedBack;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.presenters.GiveFeedbackPresenter;
import com.team.noty.event.mvp.views.GiveFeedbackView;
import com.team.noty.event.network.models.request.RqGiveFeedback;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.ui.holders.ParticipantHolder;
import com.team.noty.event.utils.ImageLoader;
import com.team.noty.event.utils.callbacks.CallBack;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class GiveFeedbackActivity extends BaseActivity implements GiveFeedbackView {

    private final static String ID = "id";

    public static void open(Context context, String id) {
        actid = id;
        context.startActivity(intent(context, id));
    }

    public static Intent intent(Context context, String id){
        return new Intent(context, GiveFeedbackActivity.class).putExtra(ID, id);
    }
    static String actid;
    @InjectPresenter
    GiveFeedbackPresenter presenter;

    @BindView(R.id.give_image)
    ImageView giveImage;
    @BindView(R.id.give_title)
    TextView giveTitle;
    @BindView(R.id.give_description_field)
    EditText giveDescriptionField;
    @BindView(R.id.give_creator)
    View creatorView;
    @BindView(R.id.give_mark_field)
    MaterialRatingBar ratingBar;
    @BindView(R.id.give_button)
    Button button;
    ParticipantHolder creatorHolder;
    RqGiveFeedback feedback;
    private int mark = -1;

    @Override
    protected int layout() {
        return R.layout.activity_give_feedback;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        creatorHolder = new ParticipantHolder(creatorView);
        feedback = new RqGiveFeedback();
        TextView textView = new TextView(this);
        textView.setText(R.string.chose);
        button.setEnabled(false);
        getId(getIntent().getExtras());
        ratingBar.setNumStars(5);
        ratingBar.setOnRatingChangeListener(this::setMark);
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        setBackArrow(bar);
        bar.setTitle(R.string.my_reviews);
    }

    private void getId(Bundle b) {
        if (b != null && b.containsKey(ID))
            presenter.load(b.getString(ID));
    }

    private void setMark(MaterialRatingBar bar, float i) {
        mark = (int)i;
        checkFields();
    }

    @OnClick(R.id.give_button)
    public void onGiveButtonClicked(Button b) {

        presenter.send(new FeedBack(String.valueOf(Math.round(ratingBar.getRating())), giveDescriptionField.getText().toString(),actid));
        b.setEnabled(false);
    }

    @Override
    public void onComplete() {
        mark = -1;
        giveDescriptionField.setText("");
        Intent intent = new Intent(this,ReviewActivity.class);
        intent.putExtra("id",presenter.event_id);
        startActivity(intent);
        checkFields();
    }

    @OnTextChanged(R.id.give_description_field)
    void textChange() {
        checkFields();
    }

    private void checkFields() {
        button.setEnabled(mark > 0 && !giveDescriptionField.getText().toString().isEmpty());
    }

    @Override
    public void loadUser(User user, int age) {
        creatorHolder.setUserId(String.valueOf(user.id()));
        creatorHolder.setRate(user.level);
        creatorHolder.hideButton();
        creatorHolder.setPhoto(user.imageUrl);
        creatorHolder.setCity(user.city);
        creatorHolder.setName_age(user.firstname, String.valueOf(age));
    }

    @Override
    public void loadEvent(String image, String title) {
        this.giveTitle.setText(title);
        ImageLoader.loadImage(image, giveImage);
    }

    @Override
    public void showError(String message) {
        DialogProvider.infoOk(this, message, () -> TabsActivity.openSearch(GiveFeedbackActivity.this)).show();
    }

    @Override
    public void showInfo(int messageId, Object... params) {
        super.showInfo(messageId, params);
        checkFields();
    }
}
