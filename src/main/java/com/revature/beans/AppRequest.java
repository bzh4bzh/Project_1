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
	private double cost;
	private double reimbursement;
	/*
	 * -1 to 4 for pending status -1: denied 0: pending @ Direct Supervisro 1:
	 * pending @ Dept Head 2: pending @ Ben Co 3:approved
	 */
	private int status;

	public AppRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppRequest(int userID, String name, String location,  String date, String description,
			int type, int gradingScale, String passingGrade ,String justification,  double cost, double reimbursement) {
		super();
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
		this.status = 0;
	}
		
		

	public AppRequest( int userID, String name, String location, String description, String date,
			int type, String justification, int gradingScale, String passingGrade, double cost, double reimbursement,
			int status) {
		super();
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
		this.reimbursement = reimbursement;
		this.status = status;
	}

	public double getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(double reimbursement) {
		this.reimbursement = reimbursement;
	}

	public void setCost(double cost) {
		this.cost = cost;
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

	public double getCost() {
		return cost;
	}

}
