package evfor.fun.skvader.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import evfor.fun.skvader.R;
import evfor.fun.skvader.models.Location;
import evfor.fun.skvader.mvp.presenters.SelectCityPresenter;
import evfor.fun.skvader.mvp.views.SelectCityView;
import evfor.fun.skvader.ui.adapters.CityAdapter;
import evfor.fun.skvader.ui.dialogs.Toaster;
import evfor.fun.skvader.models.City;
import evfor.fun.skvader.ui.utils.RecyclerUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class SelectCityActivity extends BaseActivity implements SelectCityView {

    public static final String CITY = "city";
    public static final int CODE = 25;

    @InjectPresenter
    SelectCityPresenter presenter;

    @BindView(R.id.select_city_list)
    RecyclerView recyclerView;
    private CityAdapter adapter;

    public static void open(Context context) {
        ((AppCompatActivity) context).startActivityForResult(new Intent(context, SelectCityActivity.class), CODE);
    }

    public static String getCity(Intent data) {
        if (data != null && data.getExtras() != null && data.getExtras().containsKey(CITY))
            return data.getExtras().getString(CITY);
        else return null;
    }

    @Override
    protected int layout() {
        return R.layout.activity_select_city;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            setActionBar(getSupportActionBar());
        adapter = new CityAdapter();
        adapter.setCityCallBack(this::setCity);
        RecyclerUtils.setVerticalAdapter(recyclerView, adapter);
    }

    private void setCity(String city) {
        if (city != null) {
            Intent i = new Intent();
            i.putExtra(CITY, city);
            setResult(RESULT_OK, i);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Toaster.duration(this, getString(R.string.city_not_change), 3000);
        super.onBackPressed();
    }

    private void setActionBar(ActionBar bar) {
        bar.setTitle("");
        bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    }

    @OnTextChanged(R.id.city_search)
    void searchTextChange(CharSequence text) {
        if (text.length() >= 3)
            presenter.search(text.toString());
    }

    @Override
    public void showList(List<City> cities) {
        adapter.setCities(cities);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Location latLng = SelectLocationActivity.result(data);
        if (latLng == null)
            super.onActivityResult(requestCode, resultCode, data);
        else presenter.search(latLng);
    }

    @OnClick(R.id.city_close)
    public void onViewClicked(View view) {
        finish();
    }

    @OnClick(R.id.city_location)
    public void onLocationClicked() {
        SelectLocationActivity.open(this);
    }
}
