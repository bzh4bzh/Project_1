package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.RequestsDaoImipl;

/**
 * Servlet implementation class ApproveServlet
 */
public class DenyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doPost of DenyServlet");
		if(request.getSession(false)==null) {
			response.sendRedirect("login.html");
		}
		RequestsDaoImipl rdi=new RequestsDaoImipl();
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		System.out.println(request.getParameter("requestId"));
		int recid=Integer.parseInt(request.getParameter("requestId"));
		rdi.updateStatus( -1,recid);
		int auth = edi.checkAthority(edi.getUserID((String) request.getSession().getAttribute("name")));
		if (auth == 3 || auth == 1) {
			response.sendRedirect("supHomeSubmit.html");
		} else if (auth == 2) {
			response.sendRedirect("DeptHomeSubmit.html");
		} else {
			response.sendRedirect("homeSubmit.html");
		}
	}

}

