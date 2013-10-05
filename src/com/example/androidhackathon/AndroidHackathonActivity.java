package com.example.androidhackathon;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.example.androidhackathon.photos.Photo;
import com.example.androidhackathon.photos.PhotoStore;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.androidhackathon.R;

import java.util.List;

public class AndroidHackathonActivity extends Activity implements
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener {
    private GoogleMap map;

    private final static int
            CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private HackathonLocation location;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toast.makeText(this, PhotoStore.getCameraImages(this).size() + "", Toast.LENGTH_LONG).show();
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        location = new HackathonLocation(this, map);
        List<Photo> photos = PhotoStore.getCameraImages(this);
         Photo photo = photos.get(0);
         new AlertDialog.Builder(this)
             .setTitle("Photos")
             .setMessage(photo.toString()).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        location.connect();
    }

    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        location.disconnect();
        super.onStop();
    }

    public void onConnected(Bundle bundle) {
        location.dropMarkerHere("You are here!");
    }

    public void onDisconnected() {
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
    }
}
