package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.dao.RequestsDao;
import com.revature.util.ConnFactory;

public class RequestsDaoImipl implements RequestsDao{
	public static ConnFactory cf= ConnFactory.getInstance();
	@Override
	public void insertRequest(int requestid, int useid, String eventname, String eventlocation, String eventdate, String eventdescription, int eventtype, int gradingscale, String passinggrade, String justification, double eventcost, double reimbursement, int status) {
		Connection conn = cf.getConnection();
		String sql = "{ call insertRec(?,?,?,?,?,?,?,?,?,?,?)";
		CallableStatement call;
		try {
			call = conn.prepareCall(sql);
			call.setInt(1, id);
			call.setDouble(2, bal);
			call.executeQuery();
			
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}

	}

	@Override
	public String getApplicationStatus(int id) {
			Connection conn = cf.getConnection();
			String sql = "select status from request inner join employee on request.userid=employee.userid where employee.userid=?";
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				rs.next();
				return rs.getString(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public String getPendingSuper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPendingDeptHead() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPendingBenCo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(int authority, int recid) {
		Connection conn = cf.getConnection();
		String sql = "{ call updateStatus(?,?)";
		CallableStatement call;
		try {
			call = conn.prepareCall(sql);
			call.setInt(1, authority);
			call.setDouble(2, recid);
			call.executeQuery();
			
			
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
		
	}
	@Override
	public void updateReimbursement(int id, double bal) {
		
			Connection conn = cf.getConnection();
			String sql = "{ call updateReimburse(?,?)";
			CallableStatement call;
			try {
				call = conn.prepareCall(sql);
				call.setInt(1, id);
				call.setDouble(2, bal);
				call.executeQuery();
				
			} catch (SQLException e) {
				//
				e.printStackTrace();
			}

		}
	}