package com.example.mytest;

public class Reader {
     
    private String id;
    private String name;
    private String emo;
    private String emoRes;
    private String lat;
    private String lon;
    private String date;
    private String time;
    
    public Reader(String id, String name, String emo, String emoRes, String lat, String lon, String date, String time) {
        super();
        this.id = id;
        this.name = name;
        this.emo = emo;
        this.emoRes = emoRes;
        this.lat = lat;
        this.lon = lon;
        this.date = date;
        this.time = time;
    }
 
    public Reader() {
        super();
    }
    
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmo() {
		return emo;
	}
	
	public void setEmo(String emo) {
		this.emo = emo;
	}
	
	public String getEmoRes() {
		return emoRes;
	}
	
	public void setEmoRes(String emoRes) {
		this.emoRes = emoRes;
	}
	
	public String getLat() {
		return lat;
	}
	
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	public String getLon() {
		return lon;
	}
	
	public void setLon(String lon) {
		this.lon = lon;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
     
}