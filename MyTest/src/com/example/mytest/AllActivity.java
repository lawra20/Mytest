package com.example.mytest;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class AllActivity extends MainActivity {
	
	List<Overlay> mapOverlays;
	Drawable drawable;
	MyItemizedOverlay itemizedOverlay;
	MyLocationOverlay myOverlay;
	
	TextView text;
	Handler handler = new Handler();
	String urlStr = "http://117.16.43.49:8888/AndroidXML/select.action?id=";
	String id = null;
	int s = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		mapOverlays = mapView.getOverlays();
		
		drawable = getResources().getDrawable(R.drawable.who);
		itemizedOverlay = new MyItemizedOverlay(drawable, mapView);
		
		myOverlay = new MyLocationOverlay(this, mapView);
		
		new Thread() {
			public void run() {
				
				for(int i=1; i<21; i++){
					
					id = urlStr + i;
					request(id);
				}
				
			}
		}.start();
		
	}
	
private void request(String urlStr) {
		
		String id=null;
		String name=null;
		String emo=null;
		String emoRes=null;
		String lat=null;
		String lon=null;
		String date=null;
		String time=null;
		
		String emoImg=null;
		
		Reader reader = new Reader();
		
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			URL url = new URL(urlStr);
			InputStream instream = url.openStream();
			parser.setInput(instream, "UTF-8");
			
			int eventType = parser.getEventType();
			
			while( eventType != XmlPullParser.END_DOCUMENT) {
                switch(eventType) {
                 
                case XmlPullParser.START_TAG:
                	
					String tag = parser.getName();
					
					
					if(tag.equalsIgnoreCase("id")) {
						
						reader.setId(parser.nextText());
						id = reader.getId();
						
					} 
					
					if(tag.equalsIgnoreCase("name")) {
						
						reader.setName(parser.nextText());
						name = reader.getName();
						
					} 
					
					if(tag.equalsIgnoreCase("emotionvalue")) {
						
						reader.setEmo(parser.nextText());
						emo = reader.getEmo();
						
					} 
					
					if(tag.equalsIgnoreCase("emotionresult")) {
						
						reader.setEmoRes(parser.nextText());
						emoRes = reader.getEmoRes();
						
					} 
					
					if(tag.equalsIgnoreCase("latitude")) {

						reader.setLat(parser.nextText());
						lat = reader.getLat();

					} 
					
					if(tag.equalsIgnoreCase("longitude")) {

						reader.setLon(parser.nextText());
						lon = reader.getLon();

					} 
					
					if(tag.equalsIgnoreCase("date")) {

						reader.setDate(parser.nextText());
						date = reader.getDate();

					} 
					
					if(tag.equalsIgnoreCase("time")) {

						reader.setTime(parser.nextText());
						time = reader.getTime();

					} 
					break;
				
                case XmlPullParser.END_TAG:
					
					String endTag = parser.getName();
					if(endTag.equals("androidxml")){ 
						
						Date dataDate = new Date();	// 데이터 날짜
						Date nowDate = new Date();	// 현재 날짜
						
						Date dataTime = new Date();	// 데이터 시간
						Date nowTime = new Date();	// 현재 시간
						
						SimpleDateFormat FormatNowDate = new SimpleDateFormat("yyyy-MM-dd");
						Date datedate = new Date();
						String StringNowDate = FormatNowDate.format(datedate);
						
						SimpleDateFormat FormatNowTime = new SimpleDateFormat("HH:mm:ss");
						Date datetime = new Date();
						String StringNowTime = FormatNowTime.format(datetime);
						
						nowDate = FormatNowDate.parse(StringNowDate);
						nowTime = FormatNowTime.parse(StringNowTime);
						
						String mDate = date.replace("/", "-");
						dataDate = FormatNowDate.parse(mDate);
						
						dataTime = FormatNowTime.parse(time);

						if((dataDate.before(nowDate) || dataDate.equals(nowDate)) && (dataTime.before(nowTime) || dataTime.after(nowTime))) {
							
//						if((dataDate.getMonth() == 1)) {
							switch(Integer.parseInt(emo)) {
							case 1:
								emoImg = String.valueOf(R.drawable.a);
								break;
							case 2:
								emoImg = String.valueOf(R.drawable.b);
								break;
							case 3:
								emoImg = String.valueOf(R.drawable.c);
								break;
							case 4:
								emoImg = String.valueOf(R.drawable.d);
								break;
							case 5:
								emoImg = String.valueOf(R.drawable.e);
								break;
							case 6:
								emoImg = String.valueOf(R.drawable.f);
								break;
							case 7:
								emoImg = String.valueOf(R.drawable.g);
								break;
							case 8:
								emoImg = String.valueOf(R.drawable.h);
								break;
							case 9:
								emoImg = String.valueOf(R.drawable.i);
								break;
							}	
							
							
							GeoPoint point = new GeoPoint((int)(Double.parseDouble(lat)*1E6),(int)(Double.parseDouble(lon)*1E6));
							OverlayItem overlayItem = new OverlayItem(point, emoRes+"\nId : "+id+"\nName : "+name+
									"\nDate : "+date+"\nTime : "+time, emoImg);
//								OverlayItem overlayItem2 = new OverlayItem(point, "날짜 : "+b2[(i*5)-5]+"\n시간 : "+b2[(i*5)-4], emo);
							
							itemizedOverlay.addOverlay(overlayItem);
//								itemizedOverlay.addOverlay(overlayItem2);
				
							mapOverlays.add(myOverlay);
							mapOverlays.add(itemizedOverlay);

						}
						
					}
				}

				eventType = parser.next();
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}