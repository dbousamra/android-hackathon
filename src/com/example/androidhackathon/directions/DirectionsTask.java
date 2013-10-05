package com.example.androidhackathon.directions;

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

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

public class DirectionsTask extends AsyncTask {

	String url = "http://maps.googleapis.com/maps/api/directions/json?origin=Boston,MA&destination=Concord,MA&waypoints=Charlestown,MA&sensor=false";
	private GoogleMap mMap;

	public DirectionsTask(GoogleMap map) {
		mMap = map;
	}
	
	@Override
	protected Object doInBackground(Object... params) {
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpPost httpPost = new HttpPost(url);
			HttpResponse response = httpClient.execute(httpPost, localContext);
			HttpEntity entity = response.getEntity();
			String entityString = EntityUtils.toString(entity);
			JSONObject jsonObj = new JSONObject(entityString);

			JSONArray routes = jsonObj.getJSONArray("routes");
			JSONObject route = routes.getJSONObject(0);
			JSONObject polyline = route.getJSONObject("overview_polyline");
			String points = polyline.getString("points");

			List<LatLng> listPoints = PolyUtil.decode(points);

			PolylineOptions polylineOptions = new PolylineOptions().width(3)
					.color(Color.BLUE);

			for (LatLng latLng : listPoints) {
				polylineOptions.add(latLng);
			}
			
			return polylineOptions;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Object result) {
		mMap.addPolyline((PolylineOptions) result);
	}
	
	
	
	

}
