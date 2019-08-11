package com.revature.beans;

import java.util.HashMap;

public class Holding {
	private static HashMap<String, Integer> gscale = new HashMap<String, Integer>();
	private static HashMap<String, EventType> etype = new HashMap<String, EventType>();
	private static HashMap<Integer, String> dtype = new HashMap<Integer,String>();
	{
		gscale.put("Letter", 1);
		gscale.put("Percentage", 2);
		gscale.put("Pass/Fail", 3);
		gscale.put("Other", 4);

		etype.put("University Courses", new EventType("University Courses", 0.8, 1));
		etype.put("Certification", new EventType("Certification", 1, 4));
		etype.put("Technical Training", new EventType("Technical Training", 0.9, 5));
		etype.put("Seminars", new EventType("Seminars", 0.6, 2));
		etype.put("Certification Preparation Classes", new EventType("Certification Preparation Classes", 0.75, 3));
		etype.put("Other", new EventType("Other", 0.3, 6));
		System.out.println("filled in holding");
		dtype.put(1, "IT");
		dtype.put(2, "Marketing");
		dtype.put(3, "Chemical Engineering");
		dtype.put(4, "RND");
		dtype.put(5, "QC");
		dtype.put(6, "HR");

	}
	public Holding() {
		// TODO Auto-generated constructor stub
	}
	public static HashMap<String, Integer> getGscale() {
		return gscale;
	}
	public static HashMap<String, EventType> getEtype() {
		return etype;
	}
	public static HashMap<Integer, String> getDtype() {
		return dtype;
	}
	
	
	

}
