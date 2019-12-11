package com.team.noty.event.ui.activities.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.team.noty.event.R;
import com.team.noty.event.models.FilterModel;
import com.team.noty.event.mvp.presenters.FilterPresenter;
import com.team.noty.event.mvp.views.FilterView;
import com.team.noty.event.ui.activities.BaseActivity;
import com.team.noty.event.ui.activities.SelectCityActivity;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.ui.utils.DateTimePickerUtils;
import com.team.noty.event.utils.ObjUtils;
import com.team.noty.event.utils.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterActivity extends BaseActivity implements FilterView {

    public static final String FILTER = "filter";
    public static final int RES = 321;

    @InjectPresenter
    FilterPresenter presenter;

    @BindView(R.id.filter_city_field)
    TextView filterCityField;
    @BindView(R.id.filter_date_field)
    TextView filterDateField;
    @BindView(R.id.filter_time_field)
    TextView filterTimeField;
    @BindView(R.id.filter_age_field)
    TextView filterAgeField;
    @BindView(R.id.filter_count_field)
    TextView filterCountField;
    @BindView(R.id.filter_price_field)
    TextView filterPriceField;
    @BindView(R.id.filter_sort_field)
    TextView filterSortField;
    @BindView(R.id.check_only_event)
    CheckBox checkOnlyEvent;
    @BindView(R.id.check_only_communities)
    CheckBox checkOnlyCommunities;
    private FilterModel.Sort sort = FilterModel.Sort.DATE;

    public static void start(Context context) {
        ((AppCompatActivity) context).startActivityForResult(new Intent(context, FilterActivity.class), RES);
    }

    public static void start(Context context, FilterModel model) {
        ((AppCompatActivity) context).startActivityForResult(new Intent(context, FilterActivity.class).putExtra(FILTER, model), RES);
    }

    public static FilterModel getResult(Intent data) {
        if (data != null
                && data.getExtras() != null
                && data.getExtras().containsKey(FILTER)) {
            Serializable serializable = data.getExtras().getSerializable(FILTER);
            if (serializable != null && serializable instanceof FilterModel)
                return (FilterModel) serializable;
        }
        return null;
    }

    @Override
    protected void onCreateMenu(Menu menu) {
        menu.findItem(R.id.enter_button).setTitle(R.string.accept);
    }

    @Override
    protected int menuLayout() {
        return R.menu.enter_menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.enter_button) {
            onFilterApplyButtonClicked();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int layout() {
        return R.layout.activity_filter;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        //presenter.loadCity();
        checkOnlyEvent.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(checkOnlyCommunities.isChecked()&&isChecked)
                checkOnlyCommunities.setChecked(false);
        });
        checkOnlyCommunities.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(checkOnlyEvent.isChecked()&&isChecked)
                checkOnlyEvent.setChecked(false);
        });
        sortDate();
        readExtras(getIntent());
    }

    private void readExtras(Intent intent) {
        if (intent != null) {
            Bundle b = intent.getExtras();
            if (b != null && b.containsKey(FILTER)) {
                Serializable serializable = b.getSerializable(FILTER);
                if (serializable != null && serializable instanceof FilterModel) {
                    fillFilter((FilterModel) serializable);
                }
            }
        }
    }

    private void fillFilter(FilterModel filterModel) {
        if (ObjUtils.notNull(filterModel.city))
            filterCityField.setText(filterModel.city);
        if (ObjUtils.notNull(filterModel.date))
            filterDateField.setText(filterModel.city);
        else filterDateField.setText(R.string.any_date);
        if (ObjUtils.notNull(filterModel.time))
            filterTimeField.setText(filterModel.time);
        else
            filterTimeField.setText(R.string.any_time);
        if (ObjUtils.notNull(filterModel.age) && filterModel.age > 0)
            filterAgeField.setText(String.valueOf(filterModel.age));
        else
            filterAgeField.setText(R.string.any_age);
        if (ObjUtils.notNull(filterModel.sort)) {
            filterDateField.setText(filterModel.date);
            sort = filterModel.sort;
            filterSortField.setText(getResources().getStringArray(R.array.sort_by)[0]);
        } else {
            if (ObjUtils.notNull(filterModel.pop)) {
                if (filterModel.pop.equals(FilterModel.Popular.up)) {
                    filterSortField.setText(getResources().getStringArray(R.array.sort_by)[1]);
                } else {
                    sort = FilterModel.Sort.DATE;
                    filterSortField.setText(getResources().getStringArray(R.array.sort_by)[0]);
                }
            }
        }

        checkOnlyEvent.setChecked(filterModel.only_event);
        checkOnlyCommunities.setChecked(filterModel.only_comm);
        filterCountField.setText(StringUtils.toStringEmptyIf0(filterModel.max_parts));
        filterPriceField.setText(StringUtils.toStringEmptyIf0(filterModel.price_up_to));
        presenter.initFilterModel(filterModel);
    }

    @Override
    public void setCity(String city) {
        filterCityField.setText(city);
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        bar.setTitle(R.string.filters);
        setBackArrow(bar);
    }

    @OnClick(R.id.filter_city_field)
    public void onViewClicked() {
        SelectCityActivity.open(this);
    }

    @OnClick(R.id.filter_city_cancel)
    public void onFilterCityCancelClicked() {
        filterCityField.setText("");
    }

    @OnClick(R.id.filter_apply_button)
    public void onFilterApplyButtonClicked() {
        presenter.finish();
    }

    @OnClick(R.id.filter_reset_button)
    public void onFilterResetButtonClicked() {
        presenter.loadCity();
        filterDateField.setText(R.string.any_date);
        filterTimeField.setText(R.string.any_time);
        filterAgeField.setText(R.string.any_age);
        filterSortField.setText(getResources().getStringArray(R.array.sort_by)[0]);
        filterCountField.setText("");
        filterPriceField.setText("");

        sort = FilterModel.Sort.DATE;
    }

    @OnClick(R.id.filter_date_field)
    public void onFilterDateFieldClicked() {
        DialogProvider.dateDialog(this, (datePicker, i, i1, i2) ->
                filterDateField.setText(DateTimePickerUtils.dateToString(datePicker))
        ).show();
    }

    @OnClick(R.id.filter_time_field)
    public void onFilterTimeFieldClicked() {
        DialogProvider.timeDialog(this, (timePicker, i, i1) ->
                filterTimeField.setText(DateTimePickerUtils.timeToString(timePicker))
        ).show();
    }

    @OnClick(R.id.filter_age_field)
    public void onFilterAgeFieldClicked() {
        DialogProvider.listDialog(this, Arrays.asList(getResources().getStringArray(R.array.age_limits)),
                (dialogInterface, i) ->
                        filterAgeField.setText(getResources().getStringArray(R.array.age_limits)[i]),
                (dialogInterface, i) ->
                        dialogInterface.dismiss()
        ).show();
    }

    @Override
    public void finish(FilterModel filterModel) {
        boolean canNext = true;
        filterModel.setPop(FilterModel.Popular.dates);
        if (!filterTimeField.getText().toString().equalsIgnoreCase(getString(R.string.any_time)))
            filterModel.setTime(filterTimeField.getText().toString());
        if (!filterDateField.getText().toString().equalsIgnoreCase(getString(R.string.any_date)))
            filterModel.setDate(filterDateField.getText().toString());
        if (!filterAgeField.getText().toString().equalsIgnoreCase(getString(R.string.any_age)))
            filterModel.setAge(StringUtils.toIntOr0(filterAgeField.getText().toString()));
        if (!filterPriceField.getText().toString().isEmpty()) {
            try {
                Integer.parseInt(filterPriceField.getText().toString());
            }catch (NumberFormatException e)
            {
               canNext = false;
               filterPriceField.setError("Неправильный формат");
            }
            if(canNext)
                filterModel.setPrice_up_to(StringUtils.toIntOr0(filterPriceField.getText().toString()));

        }
        if (!filterCountField.getText().toString().isEmpty()) {
            try {
                Integer.parseInt(filterCountField.getText().toString());
            }catch (NumberFormatException e)
            {
                canNext = false;
                filterCountField.setError("Неправильный формат");
            }
            if(canNext)
                filterModel.setMax_parts(StringUtils.toIntOr0(filterCountField.getText().toString()));
        }
        filterModel.setCity(filterCityField.getText().toString());
        filterModel.setOnly_event(checkOnlyEvent.isChecked());
        filterModel.setOnly_comm(checkOnlyCommunities.isChecked());

        if(sort.equals(FilterModel.Sort.NAME)) {
            filterModel.setPop(FilterModel.Popular.up);
            filterModel.setSort(null);
        }
        else {
            filterModel.setSort(sort);
            filterModel.setPop(null);
        }
        if(canNext) {
            Intent i = new Intent();
            i.putExtra(FILTER, filterModel);
            setResult(RESULT_OK, i);
            finish();
        }
    }

    @OnClick(R.id.filter_sort_field)
    public void onFilterSortFieldClicked() {
        DialogProvider.list(
                getString(R.string.sort_by),
                this,
                getResources().getStringArray(R.array.sort_by),
                this::sortDate, this::sortName
        ).show();
    }

    private void sortDate() {
        sort = FilterModel.Sort.DATE;
        filterSortField.setText(getResources().getStringArray(R.array.sort_by)[0]);
    }

    private void sortName() {
        sort = FilterModel.Sort.NAME;
        filterSortField.setText(getResources().getStringArray(R.array.sort_by)[1]);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String city = SelectCityActivity.getCity(data);
        if (city != null)
            filterCityField.setText(city);
        else
            super.onActivityResult(requestCode, resultCode, data);
    }
}
