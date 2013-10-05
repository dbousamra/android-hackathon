package com.example.androidhackathon;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HackathonLocation {
    private final AndroidHackathonActivity activity;
    private GoogleMap map;
    private LocationClient mLocationClient;

    public HackathonLocation(AndroidHackathonActivity activity, GoogleMap map) {
        mLocationClient = new LocationClient(activity, activity, activity);
        this.map = map;
        this.activity = activity;
    }

    public void connect() {
        mLocationClient.connect();
    }

    public void disconnect() {
        mLocationClient.disconnect();
    }

    public void dropMarkerHere(String title) {
        Location mCurrentLocation = mLocationClient.getLastLocation();
        map.addMarker(new MarkerOptions()
            .position(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()))
            .title(title));

    }
}
