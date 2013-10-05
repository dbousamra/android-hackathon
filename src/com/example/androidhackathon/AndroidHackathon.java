package com.example.androidhackathon;

import android.app.Activity;
import android.os.Bundle;
import com.example.AndroidHackathon.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class AndroidHackathon extends Activity {
    private GoogleMap map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
    }
}
