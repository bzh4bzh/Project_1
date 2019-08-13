package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.RequestsDaoImipl;

/**
 * Servlet implementation class NotificationServlet
 */
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("In doPost of NotificationServlet");
		if (request.getSession(false) == null) {
			response.sendRedirect("login.html");
		}
		PrintWriter out = response.getWriter();
		RequestsDaoImipl rdi = new RequestsDaoImipl();
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		int userid = edi.getUserID((String) request.getSession().getAttribute("name"));
		int deptid = edi.getDeptID((String) request.getSession().getAttribute("name"));
		int auth = edi.checkAthority(userid);
		if (auth == 0) {
			out.print(rdi.getMyPendingNotifications(userid));
		} else if (auth == 1) {
			out.print(rdi.getFlaggedSuper(userid));
		} else if (auth == 2) {
			out.print(rdi.getFlaggedDeptHead(deptid));
		} else if (auth == 3) {
			out.print(rdi.getApprovedBenCo());
		}
	}

}
