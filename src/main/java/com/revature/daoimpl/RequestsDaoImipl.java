package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.AppRequest;
import com.revature.dao.RequestsDao;
import com.revature.util.ConnFactory;

public class RequestsDaoImipl implements RequestsDao {
	public static ConnFactory cf = ConnFactory.getInstance();

	private String month(String m) {
		int mx = Integer.parseInt(m);
		switch (mx) {
		case 1:
			return "JAN";
		case 2:
			return "FEB";
		case 3:
			return "MAR";
		case 4:
			return "APR";
		case 5:
			return "MAY";
		case 6:
			return "JUN";
		case 7:
			return "JUL";
		case 8:
			return "AUG";
		case 9:
			return "SEP";
		case 10:
			return "OCT";
		case 11:
			return "NOV";
		case 12:
			return "DEC";

		}
		return null;
	}

	private String formatDate(String d) {
		String[] split = d.split("-");
		StringBuffer sb = new StringBuffer();
		sb.append(split[2] + "-");
		sb.append(this.month(split[1]) + "-");
		sb.append(split[0]);
		return sb.toString();
	}

	@Override
	public void insertRequest(int userID, String name, String location, String date, String description, int type,
			int gradingScale, String passingGrade, String justification, double cost, double reimbursement, String links) {
		Connection conn = cf.getConnection();

		// format date
		String[] split = date.split("-");
		StringBuffer sb = new StringBuffer();
		sb.append(split[2] + "-");
		sb.append(this.month(split[1]) + "-");
		sb.append(split[0]);
		date = sb.toString();
		System.out.println("the date is this now " + date);
		String sql = "{ call insertRec(?,?,?,?,?,?,?,?,?,?,?,?)";
		CallableStatement call;
		try {
			
			call = conn.prepareCall(sql);
			call.setInt(1, userID);
			call.setString(2, name);
			call.setString(3, location);
			call.setString(4, date);
			// System.out.println("The date looks like this " + date);
			call.setString(5, description);
			call.setInt(6, type);
			call.setInt(7, gradingScale);
			call.setString(8, passingGrade);
			call.setString(9, justification);
			call.setDouble(10, cost);
			call.setDouble(11, reimbursement);
			call.setString(12, links);
			call.execute();

		} catch (SQLException e) {
			//
			e.printStackTrace();
		}

	}
	
	
	public int getUserId(int recid) {
		Connection conn = cf.getConnection();
		String sql = "select userid from request where requestid=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, recid);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getRem(int recid) {
		Connection conn = cf.getConnection();
		String sql = "select reimbursment from request where requestid=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, recid);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	
	
	@Override
	public int getApplicationStatus(int id) {
		Connection conn = cf.getConnection();
		String sql = "select status from request where requestid=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
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

				aar.add(new AppRequest(rs.getInt(2), rs.getString(3), rs.getString(4),
						this.formatDate(rs.getDate(5).toString()), rs.getString(6), rs.getInt(7), rs.getInt(8),
						rs.getString(9), rs.getString(10), rs.getDouble(11), rs.getDouble(12), rs.getInt(1),
						rs.getString(14),rs.getInt(13)));
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
		String sql = "select * from request join employee on request.userid=employee.userid where status = 0 and employee.reportsto=? order by eventdate";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				aar.add(new AppRequest(rs.getInt(2), rs.getString(3), rs.getString(4),
						this.formatDate(rs.getDate(5).toString()), rs.getString(6), rs.getInt(7), rs.getInt(8),
						rs.getString(9), rs.getString(10), rs.getDouble(11), rs.getDouble(12), rs.getInt(1),
						rs.getString(14),rs.getInt(13)));
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
		String sql = "select * from request join employee on request.userid=employee.userid where status = 1 and employee.department=? order by eventdate";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, deptID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				aar.add(new AppRequest(rs.getInt(2), rs.getString(3), rs.getString(4),
						this.formatDate(rs.getDate(5).toString()), rs.getString(6), rs.getInt(7), rs.getInt(8),
						rs.getString(9), rs.getString(10), rs.getDouble(11), rs.getDouble(12), rs.getInt(1),
						rs.getString(14),rs.getInt(13)));
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
	public String getPendingBenCo() {
		Connection conn = cf.getConnection();
		ArrayList<AppRequest> aar = new ArrayList<AppRequest>();
		String sql = "select * from request where status = 2 order by eventdate";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				aar.add(new AppRequest(rs.getInt(2), rs.getString(3), rs.getString(4),
						this.formatDate(rs.getDate(5).toString()), rs.getString(6), rs.getInt(7), rs.getInt(8),
						rs.getString(9), rs.getString(10), rs.getDouble(11), rs.getDouble(12), rs.getInt(1),
						rs.getString(14),rs.getInt(13)));
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
		sb.append(
				"<!DOCTYPE html><html><head><meta charset='UTF-8'><style type='text/css'>table{border-collapse: collapse;}table tr td{border: 1px solid black;}th{background-color: gray;color: white;border: 1px solid black;}tr:nth-child(even) {background-color: #d3d3d3;}.approve{color: green;font-weight: bold;}.deny{color: red;font-weight: bold;}</style><title>Pending</title><link href=\"data:image/png;base64,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\" rel=\"icon\" type=\"image/x-icon\"/></head><body><div><table id=\"table\">");
		sb.append("<thead>\r\n"
				+ "        <th>ReqID</th><th>UsrID</th><th>Event</th><th>Location</th><th>Date</th><th>Description</th><th>EventType/Category</th><th>Grading Scale</th><th>Passing Grade</th><th>Justification</th><th>Cost</th><th>Reimb.</th><th>Status</th><th>Attachment Links</th><th>Approve/Deny</th>"
				+ "    </thead>");
		sb.append("<tbody id=\"tbody\">");
		for (AppRequest a : aar) {
			sb.append("<tr><td>" + (a.getRequestid()) + "</td>");
			sb.append("<td>" + (a.getUserID()) + "</td>");
			sb.append("<td>" + (a.getName()) + "</td>");
			sb.append("<td>" + (a.getLocation()) + "</td>");
			sb.append("<td>" + (a.getDate()) + "</td>");
			sb.append("<td>" + (a.getDescription()) + "</td>");
			sb.append("<td>" + (a.getType()) + "</td>");
			sb.append("<td>" + (a.getGradingScale()) + "</td>");
			sb.append("<td>" + (a.getPassingGrade()) + "</td>");
			sb.append("<td>" + (a.getJustification()) + "</td>");
			sb.append("<td>" + (a.getCost()) + "</td>");
			sb.append("<td>" + (a.getReimbursement()) + "</td>");
			sb.append("<td>" + (a.getStatus()) + "</td>");
			sb.append("<td>"+  (a.getLinks()) + "</td>");
			sb.append("<td><form method=\"post\" action=\"approve\"><button class=\"approve\" name=\"requestId\" value=");
			sb.append("\"" + a.getRequestid()
					+ "\" onclick= approve(value)>Approve &#9989;</button></form><form method=\"post\" action=\"deny\"><button class=\"deny\" name=\"requestId\" value=\"");
			sb.append(a.getRequestid() + "\" onclick=\"deny(value)\">Deny &#10060;</button></form></td>");
		}
		sb.append("</tbody></table></div></body>");
		sb.append("<script>window.onload = function() {let table = document.getElementById(\"tbody\");console.log(\"in window onload function\");for (var i = 0, row; row = table.rows[i]; i++){let tableDate = row.cells[4].innerHTML;tableDate = new Date(tableDate);tableDate.setFullYear(Math.abs(tableDate.getFullYear()));let curDate = new Date();curDate.setHours(0,0,0,0);if (tableDate-curDate <= 86400000*14) {row.style.color=\"red\";}else{break;}}};");
		sb.append("function approve(ReqID){ console.log(\"Approve\", ReqID); let xhr = new XMLHttpRequest(); xhr.onreadystatechange = function() { if(xhr.readyState == 4 && xhr.status == 200) { location.reload(true); } };console.log(\"requestId=\"+ReqID); xhr.open(\"POST\",\"approve\", true); xhr.send(\"requestId=\" + ReqID); }");
		sb.append("function deny(ReqID){ console.log(\"Deny\", ReqID); let xhr = new XMLHttpRequest(); xhr.onreadystatechange = function() { if(xhr.readyState == 4 && xhr.status == 200) { location.reload(true); } };console.log(\"requestId=\"+ReqID); xhr.open(\"POST\",\"deny\", true); xhr.send(\"requestId=\" + ReqID); }");
		sb.append("</script>");
		sb.append("</html>");
		
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
		String sql = "select sum(reimbursment) from request where status!= -1 and status!= 3 and request.userid=?";
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