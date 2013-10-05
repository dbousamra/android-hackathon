package com.example.androidhackathon.directions;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.example.androidhackathon.photos.Photo;
import com.example.androidhackathon.photos.PhotoStore;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

public class DirectionsTask extends AsyncTask {

	private String url = "http://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&waypoints=%s&sensor=false";
	private GoogleMap mMap;
	private List<Photo> mPhotos;

	public DirectionsTask(GoogleMap map, List<Photo> photos) {
		mMap = map;
		mPhotos = photos;
	}
	
	@Override
	protected Object doInBackground(Object... params) {
		try {
			List<LatLng> listPoints = new ArrayList<LatLng>();
			if (mPhotos != null) {
				LatLng locationStart = mPhotos.get(0).getLocation();
				LatLng locationEnd = mPhotos.get(mPhotos.size() - 1).getLocation();
				url = String.format(url, locationToString(locationStart), locationToString(locationEnd), "");
				
				listPoints.add(locationStart);
				listPoints.add(locationEnd);
			}
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpPost httpPost = new HttpPost(url);
			HttpResponse response = httpClient.execute(httpPost, localContext);
			HttpEntity entity = response.getEntity();
			String entityString = EntityUtils.toString(entity);
			JSONObject jsonObj = new JSONObject(entityString);
			
			JSONArray routes = jsonObj.getJSONArray("routes");
			if (routes.length() > 0)
			{
				JSONObject route = routes.getJSONObject(0);
				JSONObject polyline = route.getJSONObject("overview_polyline");
				String points = polyline.getString("points");
				
				listPoints = PolyUtil.decode(points);
			}

			PolylineOptions polylineOptions = new PolylineOptions().width(3)
					.color(Color.BLUE)
					.geodesic(true);

			for (LatLng latLng : listPoints) {
				polylineOptions.add(latLng);
			}
			
			return polylineOptions;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private String locationToString(LatLng locationStart) {
		return locationStart.latitude + ","+ locationStart.longitude;
	}

	@Override
	protected void onPostExecute(Object result) {
		mMap.addPolyline((PolylineOptions) result);
	}
	
	
	
	

}
