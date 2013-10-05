package com.example.androidhackathon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.androidhackathon.photos.Photo;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class PhotoMarker implements ClusterItem {
    private final Photo mPhoto;
    private final Bitmap bitmap;

    public PhotoMarker(Photo photo) {
        mPhoto = photo;
        bitmap = calcBitmap();
    }

    private Bitmap calcBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 32;
        return BitmapFactory.decodeFile(getPhoto().getPath(), options);
    }

    public LatLng getPosition() {
        return this.mPhoto.getPosition();
    }

    public Photo getPhoto() {
        return this.mPhoto;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }
}
