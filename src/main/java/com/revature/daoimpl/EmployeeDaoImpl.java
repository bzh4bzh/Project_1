package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.dao.EmployeeDao;
import com.revature.util.ConnFactory;

public class EmployeeDaoImpl implements EmployeeDao{
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public int getUserID(String username) {
		return 0;
	}

	@Override
	//returns the highest authority of a employee id
	public String checkAthority(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
