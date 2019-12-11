package com.team.noty.event.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team.noty.event.R;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.Address;
import com.team.noty.event.models.Location;
import com.team.noty.event.mvp.presenters.MapPresenter;
import com.team.noty.event.mvp.views.MapContentView;
import com.team.noty.event.ui.utils.MapControl;
import com.team.noty.event.ui.utils.MarkerFromView;
import com.team.noty.event.utils.Pair;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MapActivity extends BaseActivity implements MapContentView {

    @InjectPresenter
    MapPresenter presenter;

    @BindView(R.id.map_minus)
    ImageView mapMinus;
    @BindView(R.id.map_plus)
    ImageView mapPlus;
    private MapControl mapControl;
    boolean turnLoc;
    public static void open(Context context) {
            context.startActivity(new Intent(context, MapActivity.class));
    }

    @OnClick(R.id.map_gps)
    void myLocationClick() {
        presenter.getMyLocation();
    }


    @Override
    public void setCurrentLoc(Location loc) {
        mapControl.setCurrLocation(new LatLng(loc.latitude, loc.longitude));
    }

    @Override
    protected int layout() {
        return R.layout.activity_maps;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {



        mapControl = new MapControl((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map));
        mapControl.setAddZoomRtn(mapPlus);
        mapControl.setDifZoomRtn(mapMinus);
        mapControl.setOnClickMarker(this::onClick);
        presenter.getList();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick({R.id.map_search_layout,R.id.map_search_field})
    void onSearchClick() {
        SelectAddressActivity.startSelectAdressWOMap(this);
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        setBackArrow(bar);
        bar.setTitle(R.string.title_activity_maps);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addPoint(Act act) {
        if (mapControl.onMapReady())
            new MarkerFromView(act)
                    .generate(this)
                    .subscribe(this::addMarker);
    }

    private void onClick(Marker marker) {
        if (marker.getTag() != null && marker.getTag() instanceof Act) {
            EventActivity.open(this, (Act) marker.getTag());
        }
    }

    private void addMarker(Pair<MarkerOptions, Act> markerId) {
        Marker marker = mapControl.addMarker(markerId.getLeft());
        marker.setTag(markerId.getRight());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Address address = SelectAddressActivity.result(requestCode, resultCode, data);
        if (address == null)
            super.onActivityResult(requestCode, resultCode, data);
        else
            mapControl.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                            new LatLng(address.location.latitude, address.location.longitude),
                            15f));
    }
}
