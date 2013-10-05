package com.example.androidhackathon;

import android.content.Context;
import com.example.androidhackathon.photos.Photo;
import com.example.androidhackathon.photos.PhotoStore;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

public class PhotoMarkerCluster {

    private final Context context;
    private final HackathonLocation mLocation;
    private final List<Photo> photos;

    public PhotoMarkerCluster(Context context, HackathonLocation mLocation) {
        this.context = context;
        this.mLocation = mLocation;
        this.photos = PhotoStore.getCameraImages(context);
    }

    public void addCluster() {
        GoogleMap mMap = mLocation.getMap();
        ClusterManager<PhotoMarker> mClusterManager = new ClusterManager<PhotoMarker>(this.context, mMap);
        mMap.setOnCameraChangeListener(mClusterManager);
        for (Photo photo : photos) {
            mClusterManager.addItem(new PhotoMarker(photo.getLocation()));
        }

    }
}
