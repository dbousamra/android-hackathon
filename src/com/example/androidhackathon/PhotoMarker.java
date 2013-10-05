package com.example.androidhackathon;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

class PhotoMarker implements ClusterItem {
    private final LatLng mPosition;

    public PhotoMarker(LatLng position) {
        mPosition = position;
    }

    public LatLng getPosition() {
        return mPosition;
    }
}
