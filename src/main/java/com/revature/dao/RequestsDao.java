package com.revature.dao;

public interface RequestsDao {
	
	public abstract void insertRequest(int userID); 
	public abstract String getApplicationStatus();
	public abstract String getPendingSuper();
	public abstract String getPendingDeptHead();
	public abstract String getPendingBenCo();
	public abstract String updateStatus();
	public abstract String updateReimbursement();
}
