package com.example.androidhackathon;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.example.androidhackathon.R;
import com.example.androidhackathon.directions.DirectionsTask;
import com.example.androidhackathon.photos.PhotoStore;

public class AndroidHackathonActivity extends Activity {
    private GoogleMap mMap;
    private HackathonLocation mLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        mLocation = new HackathonLocation(this, mMap);
        new DirectionsTask(mMap, PhotoStore.getCameraImages(this)).execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLocation.connect();
    }

    @Override
    protected void onStop() {
        mLocation.disconnect();
        super.onStop();
    }

}
