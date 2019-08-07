package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDao;
import com.revature.util.ConnFactory;

public class EmployeeDaoImpl implements EmployeeDao {
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public int getUserID(String username) {
		Connection conn = cf.getConnection();
		String sql = "{ call getUID(?)";
		CallableStatement call;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, username);
			ResultSet rs = call.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	// returns the highest authority of a employee id
	public String checkAthority(int id) {
		// TODO Auto-generated method stub
		Connection conn = cf.getConnection();
		String sql = "Select titlename from title inner join employee on title.titleid=employee.authority where useid=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public double getRemainingBalance(int id) {
		Connection conn = cf.getConnection();
		String sql = "Select remainingBal from employee where userid =?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getDouble(1);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public String getDepartmentName(int id) {
		Connection conn = cf.getConnection();
		String sql = "Select deptname from title inner join employee on department.deptid=employee.department where employee.userid=?";
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
	public Employee getUser(int id) {
		Connection conn = cf.getConnection();
		String sql = "Select * from employee where userid =?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
					rs.getInt(6), rs.getInt(7), rs.getDouble(8));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateRemainingBalance(int id, double bal) {
		Connection conn = cf.getConnection();
		String sql = "{ call updateRemains(?,?)";
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
	public String authenticate(String username) {
		Connection conn = cf.getConnection();
		String sql = "select pass from employee where username = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
