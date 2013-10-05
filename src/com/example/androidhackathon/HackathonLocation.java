package com.example.androidhackathon;

import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HackathonLocation implements
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private LocationClient mLocationClient;
    private PhotoMarkerCluster mPhotoMarkerCluster;

    public HackathonLocation(AndroidHackathonActivity activity, GoogleMap map) {
        mLocationClient = new LocationClient(activity, this, this);
        this.mMap = map;
        mPhotoMarkerCluster = new PhotoMarkerCluster(activity, this);
    }

    public void connect() {
        mLocationClient.connect();
    }

    public void disconnect() {
        mLocationClient.disconnect();
    }

    private LatLng getCurrentPosition() {
        Location mCurrentLocation = mLocationClient.getLastLocation();
        return new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
    }

    public void onConnected(Bundle bundle) {
        mPhotoMarkerCluster.addCluster();
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(getCurrentPosition()));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14));
    }

    public void onDisconnected() {
        mLocationClient.disconnect();
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public GoogleMap getMap() {
        return mMap;
    }

}
