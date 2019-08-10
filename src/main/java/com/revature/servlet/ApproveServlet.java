package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.RequestsDaoImipl;

/**
 * Servlet implementation class ApproveServlet
 */
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doPost of ApproveServlet");
		if(request.getSession()==null) {
			response.sendRedirect("login.html");
		}
		EmployeeDaoImpl edi=new EmployeeDaoImpl();
		RequestsDaoImipl rdi=new RequestsDaoImipl();
		int id=edi.getUserID((String)request.getSession().getAttribute("name"));
		
		System.out.println(request.getParameterMap().size());
		int recid=Integer.parseInt(request.getParameter("requestId"));
		System.out.println("requestID is " + recid);
		rdi.updateStatus( edi.checkAthority(id),recid);
		if(rdi.getApplicationStatus(recid)==3) {
			int userid = rdi.getUserId(recid);
			edi.updateRemainingBalance(userid, edi.getRemainingBalance(userid)-rdi.getRem(recid));
		}
		response.sendRedirect("supHomeApproved.html");
		//RequestDispatcher rd = request.getRequestDispatcher("home.html");
		//rd.forward(request, response);
	}

}
