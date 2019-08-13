package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.RequestsDaoImipl;

/**
 * Servlet implementation class SubmitMoreInfo
 */
public class SubmitMoreInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In doPost of SubmitMoreInfoServlet");
		if (request.getSession(false) == null) {
			response.sendRedirect("login.html");
		}
		RequestsDaoImipl rdi = new RequestsDaoImipl();
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		int recid = Integer.parseInt(request.getParameter("requestId"));
		String newinfo = request.getParameter("info");
		String oldinfo = rdi.getInfo(recid);
		if (oldinfo == null) {
			oldinfo = "";
		}
		StringBuffer info = new StringBuffer();
		info.append(oldinfo + newinfo + "<br>");
		rdi.updateInfo(info.toString(), recid);
		rdi.updateFlagged(recid, -1);
		int auth = edi.checkAthority(edi.getUserID((String) request.getSession().getAttribute("name")));
		if (auth == 1 || auth == 3) {
			request.getRequestDispatcher("supHomeSubmit.html").forward(request, response);
		} else if (auth == 2) {
			request.getRequestDispatcher("DeptHomeSubmit.html").forward(request, response);
		} else {
			request.getRequestDispatcher("homeSubmit.html").forward(request, response);
		}
	}

}
