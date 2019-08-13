package com.revature.beans;

import java.util.HashMap;

public class Holding {
	private static HashMap<String, Integer> gscale = new HashMap<String, Integer>();
	private static HashMap<Integer, String> rgscale = new HashMap<Integer, String>();
	private static HashMap<String, EventType> etype = new HashMap<String, EventType>();
	private static HashMap<Integer, String> retype = new HashMap<Integer, String>();
	private static HashMap<Integer, String> dtype = new HashMap<Integer,String>();
	private static HashMap<Integer, String> stype = new HashMap<Integer, String>();
	
	{
		gscale.put("Letter", 1);
		gscale.put("Percentage", 2);
		gscale.put("Pass/Fail", 3);
		gscale.put("Other", 4);
		
		rgscale.put(1, "Letter");
		rgscale.put(2, "Percentage");
		rgscale.put(3, "Pass/Fail");
		rgscale.put(4,"Other");

		etype.put("University Courses", new EventType("University Courses", 0.8, 1));
		etype.put("Certification", new EventType("Certification", 1, 4));
		etype.put("Technical Training", new EventType("Technical Training", 0.9, 5));
		etype.put("Seminars", new EventType("Seminars", 0.6, 2));
		etype.put("Certification Preparation Classes", new EventType("Certification Preparation Classes", 0.75, 3));
		etype.put("Other", new EventType("Other", 0.3, 6));
		
		retype.put(1, "University Courses");
		retype.put(4, "Certification");
		retype.put(5, "Technical Training");
		retype.put(2, "Seminars");
		retype.put(3, "Certification Preparation Classes");
		retype.put(6, "Other");
		
		
		System.out.println("filled in holding");
		
		dtype.put(1, "IT");
		dtype.put(2, "Marketing");
		dtype.put(3, "Chemical Engineering");
		dtype.put(4, "RND");
		dtype.put(5, "QC");
		dtype.put(6, "HR");
		
		stype.put(-1, "Denied");
		stype.put(0, "Waiting for Direct Supervisor");
		stype.put(1, "Waiting for Department Head");
		stype.put(2, "Waiting for Benefits Coordinator");
		stype.put(3, "Waiting for Final Grade");
		stype.put(4, "Waiting for Final Approval");
		stype.put(5, "Reimbursement Sent");
		

	}
	public Holding() {
		// TODO Auto-generated constructor stub
	}
	public HashMap<String, Integer> getGscale() {
		return gscale;
	}
	public HashMap<String, EventType> getEtype() {
		return etype;
	}
	public HashMap<Integer, String> getDtype() {
		return dtype;
	}
	public HashMap<Integer, String> getStype() {
		return stype;
	}
	public HashMap<Integer, String> getRgscale() {
		return rgscale;
	}
	public HashMap<Integer, String> getRetype() {
		return retype;
	}
	
	
	
	

}
