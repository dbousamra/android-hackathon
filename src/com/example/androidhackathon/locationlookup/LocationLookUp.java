package com.example.androidhackathon.locationlookup;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

import android.os.AsyncTask;
import android.util.Log;

public class LocationLookUp extends AsyncTask<LatLng, Void, List> {
	private static final String TAG = "LocationLookUp";

	private static final String URL = null;
	private static final int TIMEOUT = 10000;

	@Override
	protected List doInBackground(LatLng... params) {
		InputStream inputStream = null;
		try {
			URLConnection connection = new URL(URL)
					.openConnection();
			connection.setConnectTimeout(TIMEOUT / 2);
			connection.setReadTimeout(TIMEOUT);
			connection.connect();
			inputStream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			
		} catch (MalformedURLException e) {
			Log.e(TAG,
					"MalformedURLException while downloading property list",
					e);
		} catch (IOException e) {
			Log.e(TAG, "IOException while downloading property list", e);
		}
		return null;
	}

	@Override
	protected void onPostExecute(List result) {
		super.onPostExecute(result);
	}
}
