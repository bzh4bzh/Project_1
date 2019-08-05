package com.revature.beans;

public class AppRequest {
	private int Requestid;
	private int userID;
	private String name;
	private String location;
	private String description;
	private String date;
	private int type;
	private String justification;
	/*
	 * 0: letter 1: out of 100 2: pass fail 3: other
	 */
	private int gradingScale;
	// default is C
	private String passingGrade;
	private int cost;
	/*
	 * -1 to 4 for pending status -1: denied 0: pending @ Direct Supervisro 1:
	 * pending @ Dept Head 2: pending @ Ben Co 3:approved
	 */
	private int status;

	public AppRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppRequest(int requestid, int userID, String name, String location, String description, String date,
			int type, String justification, int gradingScale, int cost, int status, String passingGrade) {
		super();
		Requestid = requestid;
		this.userID = userID;
		this.name = name;
		this.location = location;
		this.description = description;
		this.date = date;
		this.type = type;
		this.justification = justification;
		this.gradingScale = gradingScale;
		this.passingGrade = passingGrade;
		this.cost = cost;
		this.status = status;
	}

	public AppRequest(int requestid, int userID, String name, String location, String description, String date,
			int type, String justification, int cost, int gradingScale, int status) {
		this(requestid, userID, name, location, description, date, type, justification, gradingScale, cost, status,
				"C");
	}

	public int getRequestid() {
		return Requestid;
	}

	public void setRequestid(int requestid) {
		Requestid = requestid;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getGradingScale() {
		return gradingScale;
	}

	public void setGradingScale(int gradingScale) {
		this.gradingScale = gradingScale;
	}

	public String getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(String passingGrade) {
		this.passingGrade = passingGrade;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
