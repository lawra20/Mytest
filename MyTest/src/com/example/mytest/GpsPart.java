package com.example.mytest;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class GpsPart extends MapActivity implements LocationListener {

	MapController mc;
	MapView mapView;
	LocationManager location = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mapView = (MapView)findViewById(R.id.mapview);
		mc = mapView.getController();
		location = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.NO_REQUIREMENT);
		criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
		String best = location.getBestProvider(criteria, true);
		location.requestLocationUpdates(best, 1000, 0, GpsPart.this);
	}
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		double lat = location.getLatitude();
		double lon = location.getLongitude();
		GeoPoint newPoint = new GeoPoint((int)(lat*1E6), (int)(lon*1E6));
		mc.animateTo(newPoint);
		mc.setZoom(16);
		MapView.LayoutParams mapMarkerParams = new MapView.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, newPoint,
				MapView.LayoutParams.TOP_LEFT);
		ImageView mapMarker = new ImageView(getApplicationContext());
		mapMarker.setImageResource(R.drawable.marker);
		mapView.addView(mapMarker, mapMarkerParams);
		
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}