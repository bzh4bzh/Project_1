package com.revature.dao;

public interface RequestsDao {
	
	public abstract void insertRequest(int requestid, int useid, String eventname, String eventlocation, String eventdate, String eventdescription, int eventtype, int gradingscale, String passinggrade, String justification, double eventcost, double reimbursement, int status); 
	public abstract String getApplicationStatus(int id);
	public abstract String getPendingSuper();
	public abstract String getPendingDeptHead();
	public abstract String getPendingBenCo();
	public abstract void updateStatus(int authority, int recid);
	public abstract void updateReimbursement(int id, double bal);
}
