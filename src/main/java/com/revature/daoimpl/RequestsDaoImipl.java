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
	public void insertRequest(int userID, String name, String location, String date, String description,
			int type, int gradingScale, String passingGrade, String justification, double cost, double reimbursement) {
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
	
	public String getMyPending(int userID) {
		Connection conn = cf.getConnection();
		ArrayList<AppRequest> aar = new ArrayList<AppRequest>();
		String sql = "select * from request where request.userid=? order by eventdate";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				aar.add(new AppRequest(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getDouble(10),
						rs.getDouble(11),rs.getInt(12)));
			}
			String table = this.pendingTableToHtmlString(aar);
			return table;
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
		String sql = "select * from request join employee on request.userid=employee.userid where employee.reportsto=? order by eventdate";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				aar.add(new AppRequest(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getDouble(10),
						rs.getDouble(11),rs.getInt(12)));
			}
			String table = this.pendingTableToHtmlString(aar);
			return table;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getPendingDeptHead(int deptID) {
		Connection conn = cf.getConnection();
		ArrayList<AppRequest> aar = new ArrayList<AppRequest>();
		String sql = "select * from request join employee on request.userid=employee.userid where employee.department=? order by eventdate;";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, deptID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				aar.add(new AppRequest(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getDouble(10),
						rs.getDouble(11),rs.getInt(12)));
			}
			String table = this.pendingTableToHtmlString(aar);
			return table;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getPendingBenCo(int userID) {
		Connection conn = cf.getConnection();
		ArrayList<AppRequest> aar = new ArrayList<AppRequest>();
		String sql = "select * from request order by eventdate;";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				aar.add(new AppRequest(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getDouble(10),
						rs.getDouble(11),rs.getInt(12)));
			}
			String table = this.pendingTableToHtmlString(aar);
			return table;
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
			sb.append("<tr><td>" +(a.getRequestid()) + "</td>");
			sb.append("<tr><td>" +(a.getUserID()) + "</td>");
			sb.append("<tr><td>" +(a.getName()) + "</td>");
			sb.append("<tr><td>" +(a.getDate()) + "</td>");
			sb.append("<tr><td>" +(a.getDescription()) + "</td>");
			sb.append("<tr><td>" +(a.getType()) + "</td>");
			sb.append("<tr><td>" +(a.getGradingScale()) + "</td>");
			sb.append("<tr><td>" +(a.getPassingGrade()) + "</td>");
			sb.append("<tr><td>" +(a.getJustification()) + "</td>");
			sb.append("<tr><td>" +(a.getCost()) + "</td>");
			sb.append("<tr><td>" +(a.getReimbursement()) + "</td>");
			sb.append("<tr><td>" +(a.getStatus()) + "</td>");
			sb.append("<td><button class=\"approve\" value=");
			sb.append("\""+a.getRequestid()+" onclick=\"approve(value)\">Approve &#9989;</button><button class=\"deny\" value=\"");
			sb.append(a.getRequestid()+ "\" onclick=\"deny(value)\">Deny &#10060;</button></td></tr>");
		}
		return sb.toString();
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

	@Override
	public double getPendingBalance(int userid) {
		// TODO Auto-generated method stub
		Connection conn = cf.getConnection();
		String sql = "select remainingBal from employee where status!= -1 and status!= 3 userid=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1.0;
	}
}