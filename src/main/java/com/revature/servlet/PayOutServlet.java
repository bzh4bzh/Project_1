package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.RequestsDaoImipl;

/**
 * Servlet implementation class PayOutServlet
 */
public class PayOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getSession(false) == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.forward(request, response);
		}
		PrintWriter out = response.getWriter();
		RequestsDaoImipl rdi=new RequestsDaoImipl();
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		int auth = edi.checkAthority(edi.getUserID((String)request.getSession().getAttribute("name")));
		
		if(auth == 3) {
			out.print(rdi.getApprovedBenCo());

		} else {
			out.print(rdi.getApprovedSup());

		}
	}

}
