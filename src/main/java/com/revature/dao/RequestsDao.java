package com.revature.dao;

public interface RequestsDao {
	
	public abstract void insertRequest(int userID); 
	public abstract String getApplicationStatus(int id);
	public abstract String getPendingSuper();
	public abstract String getPendingDeptHead();
	public abstract String getPendingBenCo();
	public abstract void updateStatus(int authority, int recid);
	public abstract void updateReimbursement(int id, double bal);
}
