package com.example.androidhackathon;

import android.content.Context;
import android.util.Log;
import android.graphics.Bitmap;
import com.example.androidhackathon.photos.Photo;
import com.example.androidhackathon.photos.PhotoMarkerRenderer;
import com.example.androidhackathon.photos.PhotoStore;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.ui.IconGenerator;

import java.util.List;

public class PhotoMarkerCluster {

    private final Context context;
    private final HackathonLocation mLocation;
    private final List<Photo> photos;
    private final IconGenerator mIconGenerator;

    public PhotoMarkerCluster(Context context, HackathonLocation mLocation) {
        this.context = context;
        this.mLocation = mLocation;
        this.photos = PhotoStore.getCameraImages(context);
        Log.e("PhotoMarkerCluster", "finished");
        this.mIconGenerator = new IconGenerator(context);
    }

    public void addCluster() {
        GoogleMap mMap = mLocation.getMap();
        ClusterManager<PhotoMarker> mClusterManager = new ClusterManager<PhotoMarker>(this.context, mMap);

        for (final Photo photo : photos) {
            Bitmap icon = mIconGenerator.makeIcon();
            PhotoMarker marker = new PhotoMarker(photo);
            mClusterManager.addItem(marker);
        }

        mClusterManager.setRenderer(new PhotoMarkerRenderer(context,mMap, mClusterManager));

        mMap.setOnCameraChangeListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
    }
}
