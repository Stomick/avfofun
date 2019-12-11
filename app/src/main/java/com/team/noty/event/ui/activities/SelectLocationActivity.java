package com.team.noty.event.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team.noty.event.R;
import com.team.noty.event.models.Location;
import com.team.noty.event.mvp.presenters.SelectLocationPresenter;
import com.team.noty.event.mvp.views.SelectLocationView;
import com.team.noty.event.ui.utils.MapControl;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectLocationActivity extends BaseActivity implements SelectLocationView {

    private static final String LOCATION = "loc";
    public static final int code = 15;

    @InjectPresenter
    SelectLocationPresenter presenter;

    @BindView(R.id.map_minus)
    ImageView mapMinus;
    @BindView(R.id.map_plus)
    ImageView mapPlus;

    private Marker marker;
    private MapControl mapControl;

    public static void open(Context context) {
        ((AppCompatActivity) context).startActivityForResult(new Intent(context, SelectLocationActivity.class), code);
    }

    @Nullable
    public static Location result(Intent data) {
        if (data != null && data.getExtras() != null && data.getExtras().containsKey(LOCATION)){
            Serializable serializable = data.getSerializableExtra(LOCATION);
            if(serializable instanceof  Location)
                return (Location)serializable;
        }
        return null;
    }
    @OnClick(R.id.map_exit)
    void exit() {
        onBackPressed();
    }
    @Override
    protected int layout() {
        return R.layout.activity_select_location;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        mapControl = new MapControl((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map));
        mapControl.setAddZoomRtn(mapPlus);
        mapControl.setDifZoomRtn(mapMinus);
        mapControl.setOnClickMap(this::onClickMap);
        myLocationClick();
    }

    @OnClick(R.id.map_gps)
    void myLocationClick() {
        presenter.moveToMyLocation();
    }

    @Override
    public void setCurrentLoc(Location loc) {
        mapControl.setCurrLocation(new LatLng(loc.latitude, loc.longitude));
    }

    private void onClickMap(LatLng latLng, GoogleMap map) {
        if (marker == null)
            marker = map.addMarker(new MarkerOptions().position(latLng));
        else
            marker.setPosition(latLng);
        mapControl.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onBackPressed() {
        if (marker != null) {
            Intent intent = new Intent();
            intent.putExtra(LOCATION, new Location(marker.getPosition().latitude, marker.getPosition().longitude));
            setResult(code, intent);
        }
        super.onBackPressed();
    }
}
