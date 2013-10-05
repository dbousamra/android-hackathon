package com.example.androidhackathon.photos;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Photo implements ClusterItem {

    private final String path;
    private final LatLng location;

    public Photo(String path, LatLng location) {
        this.path = path;
        this.location = location;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "path='" + path + '\'' +
                ", location=" + location +
                '}';
    }

    @Override
    public LatLng getPosition() {
        return location;
    }
}
