package com.trendsAnalyser.app.entity;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "TrendsItem")
public class TwitterTrend {
    @Id
    private String id;
    private String trend1;
    private String trend2;
    private String trend3;
    private String trend4;
    private String trend5;
    private LocalDateTime timestamp;
    private String ipAddress;
    
	public TwitterTrend(String id, String trend1, String trend2, String trend3, String trend4, String trend5,
			LocalDateTime timestamp, String ipAddress) {
		super();
		this.id = id;
		this.trend1 = trend1;
		this.trend2 = trend2;
		this.trend3 = trend3;
		this.trend4 = trend4;
		this.trend5 = trend5;
		this.timestamp = timestamp;
		this.ipAddress = ipAddress;
	}
	public TwitterTrend() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTrend1() {
		return trend1;
	}
	public void setTrend1(String trend1) {
		this.trend1 = trend1;
	}
	public String getTrend2() {
		return trend2;
	}
	public void setTrend2(String trend2) {
		this.trend2 = trend2;
	}
	public String getTrend3() {
		return trend3;
	}
	public void setTrend3(String trend3) {
		this.trend3 = trend3;
	}
	public String getTrend4() {
		return trend4;
	}
	public void setTrend4(String trend4) {
		this.trend4 = trend4;
	}
	public String getTrend5() {
		return trend5;
	}
	public void setTrend5(String trend5) {
		this.trend5 = trend5;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

    
}

