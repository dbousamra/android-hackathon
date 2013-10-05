package com.example.androidhackathon;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

public class PhotoMarkerCluster {


    private final Context context;
    private final HackathonLocation mLocation;

    public PhotoMarkerCluster(Context context, HackathonLocation mLocation) {
        this.context = context;
        this.mLocation = mLocation;
    }

    public void addCluster() {
        GoogleMap mMap = mLocation.getMap();
        ClusterManager<PhotoMarker> mClusterManager = new ClusterManager<PhotoMarker>(this.context, mMap);
        mMap.setOnCameraChangeListener(mClusterManager);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 10; j++) {
                LatLng currentLatLng = mLocation.getCurrentLatLng();
                mClusterManager.addItem(new PhotoMarker(currentLatLng.latitude + i * 0.01, currentLatLng.longitude + j * 0.01));
            }
        }
    }
}
