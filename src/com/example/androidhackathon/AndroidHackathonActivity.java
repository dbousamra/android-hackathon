package com.example.androidhackathon;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AndroidHackathonActivity extends Activity {
    private GoogleMap map;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.addMarker(new MarkerOptions()
            .position(new LatLng(0, 0))
            .title("Hello world"));

        Toast.makeText(this, PhotoStore.getCameraImages(this).size() + "", Toast.LENGTH_LONG).show();
    }
}
