package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.AppRequest;
import com.revature.dao.RequestsDao;
import com.revature.util.ConnFactory;

public class RequestsDaoImipl implements RequestsDao {
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public void insertRequest(int requestid, int userID, String name, String location, String date, String description,
<<<<<<< HEAD
			int type, int gradingScale, String passingGrade, String justification, double cost, double reimbursement,
			int status) {
=======
			int type, int gradingScale, String passingGrade, String justification, double cost, double reimbursement) {
>>>>>>> b58512fb3790737f411a317efa8eb539fd4749f9
		Connection conn = cf.getConnection();
		String sql = "{ call insertRec(?,?,?,?,?,?,?,?,?,?,?)";
		CallableStatement call;
		try {
			call = conn.prepareCall(sql);
			call.setInt(1, userID);
			call.setString(2, name);
			call.setString(3, location);
			call.setString(4, date);
			call.setString(5, description);
			call.setInt(6, type);
			call.setInt(7, gradingScale);
			call.setString(8, passingGrade);
			call.setString(9, justification);
			call.setDouble(10, cost);
			call.setDouble(11, reimbursement);
			call.execute();

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
	public String getPendingSuper(int userID) {
		Connection conn = cf.getConnection();
		ArrayList<AppRequest> aar = new ArrayList<AppRequest>();
		String sql = "select status from request inner join employee on request.userid=employee.userid where employee.userid=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				aar.add(new AppRequest(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getDouble(10),
						rs.getDouble(11)));
			}
			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String pendingTableToHtmlString(ArrayList<AppRequest> aar) {
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html><html><head><title></title></head><body><div><table id=\"table\">");
		for(AppRequest a: aar) {
			
		}
	}
	@Override
	public String getPendingDeptHead(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPendingBenCo(int userID) {
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
<<<<<<< HEAD
=======

	@Override
	public void calcPendingReimbursement(int userId) {
		// TODO Auto-generated method stub
		
	}
>>>>>>> b58512fb3790737f411a317efa8eb539fd4749f9
}