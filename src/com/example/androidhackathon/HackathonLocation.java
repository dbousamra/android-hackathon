package com.example.androidhackathon;

import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HackathonLocation implements
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener {

    private final AndroidHackathonActivity activity;
    private GoogleMap map;
    private LocationClient mLocationClient;
    private PhotoMarkerCluster mPhotoMarkerCluster;

    public HackathonLocation(AndroidHackathonActivity activity, GoogleMap map) {
        mLocationClient = new LocationClient(activity, this, this);
        this.map = map;
        this.activity = activity;
        mPhotoMarkerCluster = new PhotoMarkerCluster(activity, this);
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

    @Override
    public void onConnected(Bundle bundle) {
        mPhotoMarkerCluster.addCluster();
        dropMarkerHere("You are here!");
    }

    @Override
    public void onDisconnected() {
        mLocationClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public GoogleMap getMap() {
        return map;
    }

    public LatLng getCurrentLatLng() {
        Location location = mLocationClient.getLastLocation();
        return new LatLng(location.getLatitude(), location.getLongitude());
    }
}
