package com.example.androidhackathon.photos;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class PhotoStore {
    private static final String CAMERA_IMAGE_BUCKET_NAME = Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera";
    private static final String CAMERA_IMAGE_BUCKET_ID = getBucketId(CAMERA_IMAGE_BUCKET_NAME);

    public static List<Photo> getCameraImages(Context context) {
        final String[] projection = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.LATITUDE,
            MediaStore.Images.Media.LONGITUDE
        };
        final String selection = MediaStore.Images.Media.BUCKET_ID + " = ?";
        final String[] selectionArgs = {
            CAMERA_IMAGE_BUCKET_ID
        };

        CursorLoader loader = new CursorLoader(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, null);

        Cursor cursor = loader.loadInBackground();

        List<Photo> result = new ArrayList<Photo>(cursor.getCount());
        if (cursor.moveToFirst()) {
            final int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            final int latColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.LATITUDE);
            final int longColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.LONGITUDE);
            do {
                if (photoHasLocation(cursor, latColumn, longColumn)) {
                    LatLng location = new LatLng(Double.parseDouble(cursor.getString(latColumn)), Double.parseDouble(cursor.getString(longColumn)));
                    Photo photo = new Photo(cursor.getString(dataColumn), location);
                    result.add(photo);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    private static boolean photoHasLocation(Cursor cursor, int latColumn, int longColumn) {
        return cursor.getString(latColumn) != null && cursor.getString(longColumn) != null;
    }

    private static String getBucketId(String path) {
        return String.valueOf(path.toLowerCase().hashCode());
    }
}