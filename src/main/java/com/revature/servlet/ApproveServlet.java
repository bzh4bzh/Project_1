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
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In doPost of ApproveServlet");
		if (request.getSession(false) == null) {
			response.sendRedirect("login.html");
		}
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		RequestsDaoImipl rdi = new RequestsDaoImipl();
		int id = edi.getUserID((String) request.getSession().getAttribute("name"));
		int recid = Integer.parseInt(request.getParameter("requestId"));
		int auth = edi.checkAthority(id);
		if (rdi.getApplicationStatus(recid) == 4) {
			rdi.updateStatus(5, recid);
			int userid = rdi.getUserId(recid);
			edi.updateRemainingBalance(userid, edi.getRemainingBalance(userid) - rdi.getRem(recid));
			
			if (auth == 3 || auth == 1) {
				rdi.updateStatus(edi.checkAthority(id), recid);
				response.sendRedirect("supHomeSubmit.html");
			} else if (auth == 2){
				rdi.updateStatus(edi.checkAthority(id), recid);
				response.sendRedirect("DeptHomeSubmit.html");
			}else {
				rdi.updateStatus(edi.checkAthority(id), recid);
				response.sendRedirect("homeSubmit.html");
			}
			// RequestDispatcher rd = request.getRequestDispatcher("home.html");
			// rd.forward(request, response);
		} else {
			if (auth == 3 || auth == 1) {
				rdi.updateStatus(edi.checkAthority(id), recid);
				response.sendRedirect("supHomeSubmit.html");
			} else if (auth == 2){
				rdi.updateStatus(2, recid);
				response.sendRedirect("DeptHomeSubmit.html");
			}else {
				rdi.updateStatus(0, recid);
				response.sendRedirect("homeSubmit.html");
			}
		}

	}
}
