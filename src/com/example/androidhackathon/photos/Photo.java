package com.example.androidhackathon.photos;

import com.google.android.gms.maps.model.LatLng;

public class Photo {

    private final String path;
    private final LatLng location;

    public Photo(String path, LatLng location) {
        this.path = path;
        this.location = location;
    }

    public String getPath() {
        return path;
    }

    public LatLng getLocation() {
        return location;
    }
}
