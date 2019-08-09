package com.revature.beans;

public class EventType {

	private String name;
	private double percent;
	private int id;
	
	
	
	public EventType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventType(String name, double percent, int id) {
		super();
		this.name = name;
		this.percent = percent;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	@Override
	public String toString() {
		return "EventType [name=" + name + ", percent=" + percent + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
