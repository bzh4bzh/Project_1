package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.ConnFactory;

public class AttachmentDaoImpl {
	public static ConnFactory cf = ConnFactory.getInstance();
	public AttachmentDaoImpl() {	
	}
	public String getAttachment(int requestId, int userId) {
		Connection conn = cf.getConnection();
		String sql = "select docs from attachment where requestid = ? and userid = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, requestId);
			ps.setInt(2, userId);
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
	public void insertAttachment(int requestId, int userId, String docs) {
		Connection conn = cf.getConnection();
		String sql = "insert into attachment values(?,?,?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, requestId);
			ps.setInt(2, userId);
			ps.setString(3,  docs);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
