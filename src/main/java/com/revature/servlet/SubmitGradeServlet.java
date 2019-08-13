package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daoimpl.RequestsDaoImipl;

/**
 * Servlet implementation class SubmitGradeServlet
 */
public class SubmitGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In doPost of SubmitGradeServlet");
		if (request.getSession(false) == null) {
			response.sendRedirect("login.html");
		}
		RequestsDaoImipl rdi = new RequestsDaoImipl();
		int recid = Integer.parseInt(request.getParameter("requestId"));
		String grade = request.getParameter("grade");
		rdi.updateFinalGrade(grade, recid);
		rdi.updateStatus(4, recid);

		request.getRequestDispatcher("homeSubmit.html").forward(request, response);
		
	}

}
