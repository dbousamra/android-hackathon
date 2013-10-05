package com.example.androidhackathon.photos;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PhotoStore {
    private static final String ROOT = Environment.getExternalStorageDirectory() + "/";
    private static final String CAMERA_IMAGE_BUCKET_NAME = Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera";
    private static final String DROPBOX_CAMERA_UPLOADS_BUCKET_NAME = Environment.getExternalStorageDirectory().toString() + "/Andriod/data/com.dropbox.android/files/scratch/";

    public static List<Photo> getCameraImages(Context context) {
        final String[] projection = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.LATITUDE,
                MediaStore.Images.Media.LONGITUDE
        };
        List<Photo> result = new ArrayList<Photo>();
        getPhotos(context, result, projection, ROOT);
        getPhotos(context, result, projection, DROPBOX_CAMERA_UPLOADS_BUCKET_NAME);
        return result;
    }

    private static void getPhotos(Context context, List<Photo> result, String[] projection, String bucketName) {
        File file = new File(bucketName);
        if (file != null && file.listFiles() != null) {
            for (File f : file.listFiles()) {
                if (f.isDirectory()) {
                    loadFilesFromDirectory(context, result, projection, f.getPath());
                    getPhotos(context, result, projection, f.getPath());
                }
            }
        }
    }

    private static void loadFilesFromDirectory(Context context, List<Photo> result, String[] projection, String bucketName) {
        final String selection = MediaStore.Images.Media.BUCKET_ID + " = ?";
        final String[] selectionArgs = {
                getBucketId(bucketName)
        };

        CursorLoader loader = new CursorLoader(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, null);

        Cursor cursor = loader.loadInBackground();

        addPhotosToList(cursor, result);
        cursor.close();
    }

    private static void addPhotosToList(Cursor cursor, List<Photo> result) {
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
    }

    private static boolean photoHasLocation(Cursor cursor, int latColumn, int longColumn) {
        return cursor.getString(latColumn) != null && cursor.getString(longColumn) != null;
    }

    private static String getBucketId(String path) {
        return String.valueOf(path.toLowerCase().hashCode());
    }
}