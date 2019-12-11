package com.team.noty.event.ui.utils;

import android.annotation.SuppressLint;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team.noty.event.utils.callbacks.CallBack1;

public class MapControl implements OnMapReadyCallback {

    private GoogleMap map;
    private float zoom;
    private OnClickMap onClickMap;
    private CallBack1<Marker> markerOnClick;

    public MapControl(SupportMapFragment mapFragment) {
        mapFragment.getMapAsync(this);
    }

    public void setAddZoomRtn(View addZoomRtn) {
        if (addZoomRtn != null)
            addZoomRtn.setOnClickListener(view -> addZoom());
    }

    public boolean onMapReady(){
        return map!=null;
    }

    public void setDifZoomRtn(View difZoomRtn) {
        if (difZoomRtn != null)
            difZoomRtn.setOnClickListener(view -> difZoom());
    }

    public void setOnClickMarker(CallBack1<Marker> callBack) {
        this.markerOnClick = callBack;
        if (map != null)
            map.setOnMarkerClickListener(this::onMarkerClick);
    }

    private boolean onMarkerClick(Marker marker) {
        if (markerOnClick != null) {
            markerOnClick.call(marker);
            return true;
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        zoom = map.getCameraPosition().zoom;
        map.setOnMarkerClickListener(this::onMarkerClick);
        map.getUiSettings().setMapToolbarEnabled(false);
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setOnCameraMoveListener(() -> zoom = map.getCameraPosition().zoom);
        map.setOnMapClickListener(latLng -> {
            if (onClickMap != null)
                onClickMap.onClick(latLng, map);
        });
    }

    @SuppressLint("MissingPermission")
    public void setCurrLocation(LatLng curr) {
        if (map != null) {
            map.setMyLocationEnabled(true);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(curr, 10f));
            zoom = 10f;
        }
    }

    public void setOnClickMap(OnClickMap onClickMap) {
        this.onClickMap = onClickMap;
    }

    private void addZoom() {
        if (map != null && zoom < map.getMaxZoomLevel())
            zoom++;
        setZoom();
    }

    private void difZoom() {
        if (map != null && zoom > map.getMinZoomLevel())
            zoom--;
        setZoom();
    }

    private void setZoom() {
        if (map != null) {
            CameraPosition position = new CameraPosition.Builder()
                    .target(map.getCameraPosition().target)
                    .zoom(zoom).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(position));
        }
    }

    public void moveCamera(CameraUpdate cameraUpdate) {
        map.moveCamera(cameraUpdate);
        zoom = map.getCameraPosition().zoom;
    }

    public interface OnClickMap {
        void onClick(LatLng latLng, GoogleMap map);
    }

    public Marker addMarker(MarkerOptions markerOptions) {
        return map.addMarker(markerOptions);
    }
}
