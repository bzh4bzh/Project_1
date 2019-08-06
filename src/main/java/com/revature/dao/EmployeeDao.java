package com.revature.dao;

public interface EmployeeDao {

	public abstract int getUserID(String username);
	public abstract String checkAthority(int id); 
}
