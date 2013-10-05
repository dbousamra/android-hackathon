package com.example.androidhackathon;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import com.example.androidhackathon.photos.Photo;
import com.example.androidhackathon.photos.PhotoStore;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

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


        List<Photo> photos = PhotoStore.getCameraImages(this);
        Photo photo = photos.get(0);
        new AlertDialog.Builder(this)
            .setTitle("Photos")
            .setMessage(photo.toString()).show();
    }
}
