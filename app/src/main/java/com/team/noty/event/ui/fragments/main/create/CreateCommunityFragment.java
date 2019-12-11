package com.team.noty.event.ui.fragments.main.create;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.team.noty.event.R;
import com.team.noty.event.models.Category;
import com.team.noty.event.models.Community;
import com.team.noty.event.models.Act;
import com.team.noty.event.mvp.views.CreateEventCommView;
import com.team.noty.event.ui.activities.SelectAddressActivity;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.ui.utils.DateTimePickerUtils;
import com.team.noty.event.ui.utils.ViewUtils;
import com.team.noty.event.utils.ImageLoader;
import com.team.noty.event.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateCommunityFragment extends BaseCreateFragment implements CreateEventCommView {

    public static final String TAG = CreateCommunityFragment.class.getSimpleName();

    @BindView(R.id.create_comm_add_imageView)
    ImageView createCommAddImageView;
    @BindView(R.id.create_comm_add_imege_icon)
    ImageView createCommAddImegeIcon;
    @BindView(R.id.create_comm_add_image)
    FrameLayout createCommAddImage;
    @BindView(R.id.create_community_title)
    EditText createCommunityTitle;
    @BindView(R.id.create_comm_add_description)
    EditText createCommAddDescription;
    @BindView(R.id.create_comm_add_video)
    EditText createCommAddVideo;
    @BindView(R.id.create_community_max_parts)
    EditText createCommunityMaxParts;
    @BindView(R.id.create_community_price)
    EditText createCommunityPrice;
    @BindView(R.id.create_community_moderation)
    Switch createCommunityModeration;
    @BindView(R.id.create_community_category)
    TextView createCommunityCategory;
    @BindView(R.id.create_community_days)
    TextView createCommunityDays;
    @BindView(R.id.create_community_time_begin)
    TextView createCommunityTimeBegin;
    @BindView(R.id.create_community_time_end)
    TextView createCommunityTimeEnd;
    @BindView(R.id.create_community_location)
    TextView createCommunityLocation;
    @BindView(R.id.create_community_age_limit)
    TextView createCommunityAgeLimit;
    @BindView(R.id.create_add_more)
    TextView createAddMore;
    @BindView(R.id.create_community_publish_button)
    Button button;
    private boolean[] days = new boolean[]{false, false, false, false, false, false, false};

    @Override
    protected int layout() {
        return R.layout.fragment_create_community;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        if (act == null)
            act = new Community();
        else presenter.getComm(act.id);
    }

    @Override
    public void loadEventComm(Act act) {
        this.act = act;
        createCommunityLocation.setText(act.address);
        createCommunityAgeLimit.setText(getAgeLimit(act.age_limit));
        categoryId = act.category_id;
        if (createCommunityCategory.getText().toString().equals(getString(R.string.chose_category)))
            createCommunityCategory.setText(categoryTitle(categoryId));
        createCommunityMaxParts.setText(StringUtils.toStringEmptyIf0(act.max_count));
        createCommAddDescription.setText(act.description);
        if (act.imageUrl != null) {
            ImageLoader.loadImage(act.imageUrl, createCommAddImageView);
            createCommAddImegeIcon.setVisibility(View.GONE);
        }
        toBool(((Community) act).days.toString());
        createCommunityPrice.setText(getPrice(act.price));
        createCommunityTitle.setText(act.title);
        createCommunityModeration.setChecked(act.mod);
        createCommunityDays.setText(act.displayDate());
        createCommunityTimeEnd.setText(((Community) act).eTime);
        createCommunityTimeBegin.setText(((Community) act).bTime);
    }

    @Override
    public void setCategories(List<Category> categories) {
        super.setCategories(categories);
        if (createCommunityCategory.getText().toString().equals(getString(R.string.chose_category)))
            createCommunityCategory.setText(categoryTitle(categoryId));
    }

    private void toBool(String days) {
        for (int i = 0; i < this.days.length; i++)
            if (days.contains(String.valueOf(i)))
                this.days[i] = true;
    }

    @Override
    public void clear() {
        super.clear();
        ViewUtils.setTextView(createCommunityLocation, R.string.chose_location);
        ViewUtils.setTextView(createCommunityMaxParts, 0);
        ViewUtils.setTextView(createCommAddDescription, 0);
        ViewUtils.setTextView(createCommAddVideo, 0);
        ViewUtils.setTextView(createCommunityPrice, 0);
        ViewUtils.setTextView(createCommunityTimeBegin, R.string.chose_time_begin);
        ViewUtils.setTextView(createCommunityTimeEnd, R.string.chose_time_end);
        ViewUtils.setTextView(createCommunityTitle, 0);
        ViewUtils.setTextView(createCommunityDays, R.string.chose_day_visits);
        ViewUtils.setTextView(createCommunityCategory, R.string.chose_category);
        ViewUtils.setTextView(createCommunityAgeLimit, R.string.age_limit);
        createCommAddImageView.setImageResource(android.R.color.transparent);
        createCommAddImegeIcon.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.create_community_category)
    public void onCreateCommunityCategoryClicked() {
        List<String> categoriesTitle = new ArrayList<>();
        for (Category category : categories)
            categoriesTitle.add(category.name);
        DialogProvider.listDialog(getContext(),
                categoriesTitle,
                (dialogInterface, i) -> {
                    categoryId = categories.get(i).id;
                    createCommunityCategory.setText(categories.get(i).name);
                },
                (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    @OnClick(R.id.create_community_days)
    public void onCreateCommunityDaysClicked() {
        DialogProvider.daysDialog(getContext(),
                days,
                (dialogInterface, i) -> onDayMultiCheck())
                .show();
    }

    @OnClick(R.id.create_community_time_begin)
    public void onCreateCommunityTimeBeginClicked() {
        DialogProvider.timeDialog(getContext(), this::onSTimeSet).show();
    }

    void onDayMultiCheck() {
        StringBuilder days = new StringBuilder();
        for (int i = 0; i < this.days.length; i++) {
            if (this.days[i]) {
                days.append(getResources().getStringArray(R.array.days_of_week_short)[i]);
                days.append(",");
            }
        }
        try {
            days.deleteCharAt(days.length() - 1);
            createCommunityDays.setText(days.toString().isEmpty() ? getString(R.string.chose_day_visits) : days.toString());
        }
        catch(Exception e){Log.e("my",e.getMessage());}

    }

    @OnClick(R.id.create_community_time_end)
    public void onCreateCommunityTimeEndClicked() {
        DialogProvider.timeDialog(getContext(), this::onETimeSet).show();
    }

    private boolean checkValid() {
        return ViewUtils.checkNotEqualsTempTV(createCommunityDays, getString(R.string.chose_day_visits), getString(R.string.fill_field)) &&
                ViewUtils.checkNotEqualsTempTV(createCommunityTimeBegin, getString(R.string.chose_time_begin), getString(R.string.fill_field)) &&
                ViewUtils.checkNotEqualsTempTV(createCommunityTimeEnd, getString(R.string.chose_time_end), getString(R.string.fill_field)) &&
                ViewUtils.checkNotEqualsTempTV(createCommunityAgeLimit, getString(R.string.age_limit), getString(R.string.fill_field)) &&
                ViewUtils.checkEmptyTV(createCommunityTitle, getString(R.string.name_community)) &&
                ViewUtils.checkNotEqualsTempTV(createCommunityLocation, getString(R.string.chose_location), getString(R.string.fill_field)) &&
                ViewUtils.checkNotEqualsTempTV(createCommunityCategory, getString(R.string.chose_category), getString(R.string.fill_field));
    }

    @OnClick(R.id.create_comm_add_image)
    public void onAddImage() {
        getImage();
    }

    @Override
    protected void setImage(String path) {
        createCommAddImageView.setImageBitmap(BitmapFactory.decodeFile(path));
        createCommAddImegeIcon.setVisibility(View.GONE);
    }

    protected void onSTimeSet(TimePicker timePicker, int i, int i1) {
        try {
            createCommunityTimeBegin.setText(DateTimePickerUtils.timeToString(timePicker));
        }catch (Exception e){}
    }

    protected void onETimeSet(TimePicker timePicker, int i, int i1) {
        try {
            createCommunityTimeEnd.setText(DateTimePickerUtils.timeToString(timePicker));
        }catch (Exception e){}
    }

    @OnClick(R.id.create_community_location)
    public void onCreateCommunityLocationClicked() {
        SelectAddressActivity.startSelectAddress(getContext());
    }

    @OnClick(R.id.create_community_age_limit)
    public void onCreateCommunityAgeLimitClicked() {
        DialogProvider.listDialog(getContext(), Arrays.asList(getResources().getStringArray(R.array.age_limits)),
                (dialogInterface, i) -> age_selected = i,
                (dialogInterface, i) -> {
                    createCommunityAgeLimit.setText(getResources().getStringArray(R.array.age_limits)[age_selected]);
                    dialogInterface.dismiss();
                }).
                show();
    }

    @OnClick(R.id.create_community_publish_button)
    public void onCreateCommunityPublishButtonClicked() {
        if (!checkValid())
            return;
        button.setEnabled(false);
        act.title = createCommunityTitle.getText().toString();
        act.age_limit = StringUtils.toIntOr0(createCommunityAgeLimit.getText().toString());
        act.address = createCommunityLocation.getText().toString();
        ((Community) act).bTime = createCommunityTimeBegin.getText().toString();
        ((Community) act).eTime = createCommunityTimeEnd.getText().toString();
        act.category_id = categoryId;
        ((Community) act).days = createDays();
        act.description = createCommAddDescription.getText().toString();
        act.imageUrl = imagePath;
        act.max_count = StringUtils.toIntOr0(createCommunityMaxParts.getText().toString());
        act.price = StringUtils.toIntOr0(createCommunityPrice.getText().toString());
        act.mod = createCommunityModeration.isChecked();
        presenter.create(act);
        showLoad();
    }

    private List<String> createDays() {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < days.length; i++)
            if (days[i])
                res.add(String.valueOf(i));
        return res;
    }

    @Override
    public boolean onBackPressed() {
        if (add) {
            hideAdditions(createCommAddImage, createCommAddDescription, createCommAddVideo, createAddMore);
            return true;
        } else
            return super.onBackPressed();
    }

    @OnClick(R.id.create_add_more)
    public void onCreateAddMoreClicked() {
        showAdditions(createCommAddImage, createCommAddDescription, createCommAddVideo, createAddMore);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        onResult(requestCode, resultCode, data, createCommunityLocation);
    }
}
