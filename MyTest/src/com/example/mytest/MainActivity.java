package com.example.mytest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity {

	MapView mapView;
	MapController mc;
	GeoPoint point;
	
	boolean[] uBoolean = {false, false, false, false, false, false};
	boolean[] tBoolean = {false, false, false, false, false, false, false, false, false, false, false, false};
	boolean[] eBoolean = {false, false, false, false, false, false, false, false, false};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
			
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		point = new GeoPoint((int)(37.571454*1E6),(int)(126.989121*1E6));
		mc = mapView.getController();
		mc.animateTo(point);
		mc.setZoom(16);
		
		Button button01 = (Button) findViewById(R.id.button01);
		Button button02 = (Button) findViewById(R.id.button02);
		Button button03 = (Button) findViewById(R.id.button03);
		
		button01.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				Intent intent = new Intent(getBaseContext(), ActionActivity.class);
				startActivity(intent);
				
			}
			
		});
		
		button02.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				Intent intent = new Intent(getBaseContext(), AllActivity.class);
				startActivity(intent);
        	
			}
			
		});
		
		button03.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				Intent intent = new Intent(getBaseContext(), GpsPart.class);
				startActivity(intent);
        	
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()) {
		case R.id.userName:
			UserName();
			return true;
			
		case R.id.usedTime:
			UsedTime();
			return true;
			
		case R.id.emoState:
			EmoState();
			return true;
			
			default:
				return super.onOptionsItemSelected(item);
		}

	}
	
	private void UserName(){
		final String[] user = {"Junho", "Sehee", "Jieun", "Jungsu", "Sungwong", "Dongku"};
		AlertDialog.Builder alt_bid = new AlertDialog.Builder(this);
//		alt_bid.setIcon(R.drawable.icon05);
		alt_bid.setTitle("이용시간을 선택하세요");		
		alt_bid.setMultiChoiceItems(user, null, new OnMultiChoiceClickListener(){
			public void onClick(DialogInterface dialog, int which, boolean isChecked){
				
				// 아이템 눌렀을 때
				uBoolean[which] = isChecked;
				
			}
		}).setPositiveButton("선택", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				for(int i=0; i<user.length; i++) {
					if(uBoolean[i]) {
						
						Intent intent = new Intent (getBaseContext(), SelectUser.class);
						intent.putExtra("사용자", user[i]);
						intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
						startActivity(intent);
					}
				}
				
			}
		}).setNegativeButton("닫기", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				
			}
		}).create().show();
		
	}
	
	private void UsedTime(){
		final String[] month = {"01월", "02월", "03월", "04월", "05월", "06월", "07월", "08월","09월","10월","11월","12월"};
		AlertDialog.Builder alt_bid = new AlertDialog.Builder(this);
//		alt_bid.setIcon(R.drawable.icon05);
		alt_bid.setTitle("이용시간을 선택하세요");		
		alt_bid.setMultiChoiceItems(month, null, new OnMultiChoiceClickListener(){
			public void onClick(DialogInterface dialog, int which, boolean isChecked){
				
				// 아이템 눌렀을 때
				tBoolean[which] = isChecked;
				
			}
		}).setPositiveButton("선택", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				for(int i=0; i<month.length; i++) {
					if(tBoolean[i]) {
						
						Intent intent = new Intent (getBaseContext(), SelectTime.class);
						intent.putExtra("시간", month[i]);
						intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
						startActivity(intent);
					}
				}
				
			}
		}).setNegativeButton("닫기", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				
			}
		}).create().show();
		
	}
	
	private void EmoState() {
		final String[] emotions = {"1", "2", "3", "4", "5", "6", "7", "8","9"};
		AlertDialog.Builder alt_bid = new AlertDialog.Builder(this);
//		alt_bid.setIcon(R.drawable.icon02);
		alt_bid.setTitle("감성 상태를 선택하세요");		
		alt_bid.setMultiChoiceItems(emotions, null, new OnMultiChoiceClickListener(){
			public void onClick(DialogInterface dialog, int which, boolean isChecked){
				
				// 아이템 눌렀을 때
				eBoolean[which] = isChecked;
				
			}
		}).setPositiveButton("선택", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
					
					for(int i=0; i<emotions.length; i++) {
						if(eBoolean[i]) {
							
							Intent intent = new Intent (getBaseContext(), SelectEmo.class);
							intent.putExtra("감성", emotions[i]);
							intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
							startActivity(intent);
						}
					}
				
					// 선택 눌렀을 때

			}
		}).setNegativeButton("닫기", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// 닫기 눌렀을 때
			
			}
		}).create().show();
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}