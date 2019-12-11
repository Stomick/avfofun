package com.team.noty.event.ui.fragments.main.create;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.team.noty.event.R;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.Category;
import com.team.noty.event.models.Event;
import com.team.noty.event.mvp.views.CreateEventCommView;
import com.team.noty.event.ui.activities.SelectAddressActivity;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.ui.utils.DateTimePickerUtils;
import com.team.noty.event.ui.utils.ViewUtils;
import com.team.noty.event.utils.DateFormatter;
import com.team.noty.event.utils.ImageLoader;
import com.team.noty.event.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateEventFragment extends BaseCreateFragment implements CreateEventCommView {

    public static final String TAG = CreateEventFragment.class.getSimpleName();

    @BindView(R.id.create_add_more)
    View addMore;
    @BindView(R.id.create_event_add_imageView)
    ImageView createEventAddImageView;
    @BindView(R.id.create_event_add_image)
    FrameLayout createEventAddImage;
    @BindView(R.id.create_event_title)
    EditText createEventTitle;
    @BindView(R.id.create_event_add_description)
    EditText createEventAddDescription;
    @BindView(R.id.create_event_max_parts)
    EditText createEventMaxParts;
    @BindView(R.id.create_event_price)
    EditText createEventPrice;
    @BindView(R.id.create_event_moderation)
    Switch createEventModeration;
    @BindView(R.id.create_event_category)
    TextView createEventCategory;
    @BindView(R.id.create_event_date)
    TextView createEventDate;
    @BindView(R.id.create_event_time)
    TextView createEventTime;
    @BindView(R.id.create_event_location)
    TextView createEventLocation;
    @BindView(R.id.create_event_age_limit)
    TextView createEventAgeLimit;
    @BindView(R.id.create_event_add_imege_icon)
    ImageView createEventAddImegeIcon;
    @BindView(R.id.create_event_add_video)
    EditText createEventAddVideo;
    @BindView(R.id.create_event_publish_button)
    Button createEventPublishButton;

    @Override
    protected int layout() {
        return R.layout.fragment_create_event;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        if (act == null)
            act = new Event();
        else presenter.getEvent(act.id);
    }

    @Override
    public void loadEventComm(Act eventComm) {
        this.act = eventComm;
        createEventLocation.setText(eventComm.address);
        createEventAgeLimit.setText(getAgeLimit(eventComm.age_limit));
        createEventDate.setText(((Event) eventComm).date);
        categoryId = eventComm.category_id;
        if (createEventCategory.getText().toString().equals(getString(R.string.chose_category)))
            createEventCategory.setText(categoryTitle(categoryId));
        createEventMaxParts.setText(StringUtils.toStringEmptyIf0(eventComm.max_count));
        createEventAddDescription.setText(eventComm.description);
        if (eventComm.imageUrl != null) {
            ImageLoader.loadImage(eventComm.imageUrl, createEventAddImageView);
            createEventAddImegeIcon.setVisibility(View.GONE);
        }
        createEventPrice.setText(getPrice(eventComm.price));
        createEventTime.setText(((Event) eventComm).time);
        createEventTitle.setText(eventComm.title);
        createEventModeration.setChecked(eventComm.mod);
    }

    @Override
    public void setCategories(List<Category> categories) {
        super.setCategories(categories);
        if (createEventCategory.getText().toString().equals(getString(R.string.chose_category)))
            createEventCategory.setText(categoryTitle(categoryId));
    }

    @OnClick(R.id.create_event_category)
    public void onCreateEventCategoryClicked() {
        List<String> categoriesTitle = new ArrayList<>();
        for (Category category : categories)
            categoriesTitle.add(category.name);
        DialogProvider.listDialog(getContext(),
                categoriesTitle,
                (dialogInterface, i) -> {
                    categoryId = categories.get(i).id;
                    createEventCategory.setText(categories.get(i).name);
                },
                (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    @OnClick(R.id.create_event_date)
    public void onCreateEventDateClicked() {
        DialogProvider.dateDialog(getContext(), createEventDate.getText().toString(), this::onDateSet).show();
    }

    @OnClick(R.id.create_event_time)
    public void onCreateEventTimeClicked() {
        DialogProvider.timeDialog(getContext(), this::onTimeSet).show();
    }

    @OnClick(R.id.create_event_location)
    public void onCreateEventLocationClicked() {
        SelectAddressActivity.startSelectAddress(getContext());
    }

    @OnClick(R.id.create_event_age_limit)
    public void onCreateEventAgeLimitClicked() {
        DialogProvider.listDialog(getContext(),
                Arrays.asList(getResources().getStringArray(R.array.age_limits)),
                (dialogInterface, i) -> age_selected = i,
                (dialogInterface, i) -> {
                    createEventAgeLimit.setText(getResources().getStringArray(R.array.age_limits)[age_selected]);
                    dialogInterface.dismiss();
                })
                .show();
    }

    @OnClick(R.id.create_event_publish_button)
    public void onCreateEventPublishButtonClicked() {
        if (!checkValid())
            return;
        createEventPublishButton.setEnabled(false);
        act.address = createEventLocation.getText().toString();
        act.age_limit = StringUtils.toIntOr0(createEventAgeLimit.getText().toString());
        act.category_id = categoryId;
        ((Event) act).date = DateFormatter.correctFormat(createEventDate.getText().toString());
        int a = StringUtils.toIntOr0((createEventMaxParts.getText().toString()));
        act.max_count = StringUtils.toIntOr0((createEventMaxParts.getText().toString())) == 0 ? 9999 : StringUtils.toIntOr0((createEventMaxParts.getText().toString()));
        act.description = createEventAddDescription.getText().toString();
        act.imageUrl = imagePath;
        act.price = StringUtils.toIntOr0(createEventPrice.getText().toString());
        ((Event) act).time = createEventTime.getText().toString();
        act.title = createEventTitle.getText().toString();
        act.mod = createEventModeration.isChecked();
        presenter.create(act);
        showLoad();

    }

    private boolean checkValid() {
        return ViewUtils.checkEmptyTV(createEventTime, getString(R.string.fill_field)) &&
                ViewUtils.checkEmptyTV(createEventTitle, getString(R.string.fill_field)) &&
                ViewUtils.checkNotEqualsTempTV(createEventDate, getString(R.string.chose_date), getString(R.string.fill_field)) &&
                ViewUtils.checkNotEqualsTempTV(createEventTime, getString(R.string.chose_time), getString(R.string.fill_field)) &&
                ViewUtils.checkNotEqualsTempTV(createEventLocation, getString(R.string.chose_location), getString(R.string.fill_field)) &&
                ViewUtils.checkNotEqualsTempTV(createEventCategory, getString(R.string.chose_category), getString(R.string.fill_field));
    }

    @Override
    public void clear() {
        super.clear();
        ViewUtils.setTextView(createEventLocation, R.string.chose_location);
        ViewUtils.setTextView(createEventMaxParts, 0);
        ViewUtils.setTextView(createEventAddDescription, 0);
        ViewUtils.setTextView(createEventAddVideo, 0);
        ViewUtils.setTextView(createEventPrice, 0);
        ViewUtils.setTextView(createEventTime, R.string.chose_time);
        ViewUtils.setTextView(createEventTitle, 0);
        ViewUtils.setTextView(createEventDate, R.string.chose_date);
        ViewUtils.setTextView(createEventCategory, R.string.chose_category);
        ViewUtils.setTextView(createEventAgeLimit, R.string.age_limit);
        createEventAddImageView.setImageResource(android.R.color.transparent);
        createEventAddImegeIcon.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.create_event_add_image)
    public void onAddImage() {
        getImage();
    }

    @Override
    protected void setImage(String path) {
        createEventAddImageView.setImageBitmap(BitmapFactory.decodeFile(path));
        createEventAddImegeIcon.setVisibility(View.GONE);
    }

    protected void onTimeSet(TimePicker timePicker, int i, int i1) {
        Log.e("my", String.valueOf(timePicker.getCurrentMinute()));
        try {
            String fd = DateTimePickerUtils.timeToString(timePicker);
            createEventTime.setText(fd);

        } catch (Exception e) {
            Log.e("my", e.getMessage());
        }
    }

    protected void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        cal.set(Calendar.MONTH, datePicker.getMonth());
        cal.set(Calendar.YEAR, datePicker.getYear());
        datePicker.setMinDate(System.currentTimeMillis());


        if (cal.getTimeInMillis() > System.currentTimeMillis() - 1000 * 60 * 60 * 24)
            try {
                createEventDate.setText(DateTimePickerUtils.dateToString(datePicker));
            } catch (Exception e) {
                Log.e("my", e.getMessage());
            }
        else
            Toast.makeText(getContext(), R.string.correct_date, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onBackPressed() {
        if (add) {
            hideAdditions(createEventAddImage, createEventAddDescription, createEventAddVideo, addMore);
            return true;
        } else
            return false;
    }

    @OnClick(R.id.create_add_more)
    public void onCreateAddMore() {
        showAdditions(createEventAddImage, createEventAddDescription, createEventAddVideo, addMore);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        onResult(requestCode, resultCode, data, createEventLocation);
    }
}
