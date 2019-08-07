package com.revature.dao;

public interface RequestsDao {
	
	public abstract void insertRequest(int requestid, int userID, String name, String location, String date, String description, int type, int gradingScale, String passingGrade, String justification, double cost, double reimbursement, int status); 
	public abstract String getApplicationStatus(int id);
	public abstract String getPendingSuper();
	public abstract String getPendingDeptHead();
	public abstract String getPendingBenCo();
	public abstract void updateStatus(int authority, int recid);
	public abstract void updateReimbursement(int id, double bal);
}
