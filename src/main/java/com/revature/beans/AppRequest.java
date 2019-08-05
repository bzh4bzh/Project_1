package com.revature.beans;

public class AppRequest {

	private String name;
	private String location;
	private String description;
	private String date;
	private String type;
	
	
	
	public AppRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppRequest(String name, String location, String description, String date, String type) {
		super();
		this.name = name;
		this.location = location;
		this.description = description;
		this.date = date;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
