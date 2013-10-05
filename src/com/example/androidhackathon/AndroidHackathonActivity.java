package com.example.androidhackathon;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.androidhackathon.photos.Photo;
import com.example.androidhackathon.photos.PhotoStore;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

public class AndroidHackathonActivity extends Activity {
	private GoogleMap map;
	private ClusterManager<MyItem> mClusterManager;
	
	private class MyItem implements ClusterItem {
	    private final LatLng mPosition;

	    public MyItem(double lat, double lng) {
	        mPosition = new LatLng(lat, lng);
	    }

	    @Override
	    public LatLng getPosition() {
	        return mPosition;
	    }
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		mClusterManager = new ClusterManager<MyItem>(this, map);
		map.setOnCameraChangeListener(mClusterManager);
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 10; j++) {
				mClusterManager.addItem(new MyItem(i * 0.01, j * 0.01));
			}
		}

		List<Photo> photos = PhotoStore.getCameraImages(this);
        Photo photo = photos.get(0);
        new AlertDialog.Builder(this)
            .setTitle("Photos")
            .setMessage(photo.toString()).show();
		
		Toast.makeText(this, PhotoStore.getCameraImages(this).size() + "",
				Toast.LENGTH_LONG).show();
	}

}
