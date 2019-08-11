package com.revature.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.EventType;
import com.revature.beans.Holding;
import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.RequestsDaoImipl;

/**
 * Servlet implementation class FormServlet
 */
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession(false) == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.forward(request, response);
		}
		System.out.println("In doPost of FormServlet");
		Holding h = new Holding();
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		RequestsDaoImipl rdi = new RequestsDaoImipl();
		System.out.println((String) request.getSession().getAttribute("name") + " is the session username");
		int id = edi.getUserID((String) request.getSession().getAttribute("name"));
		
		String name = request.getParameter("name");
		String loc = request.getParameter("location");
		String date = request.getParameter("date");
		String desc = request.getParameter("description");
		System.out.println("name is " + desc);
		String pgrade = request.getParameter("passingGrade");

		String just = request.getParameter("justification");
		System.out.println("just is " + just);
		String cost = request.getParameter("cost");
		System.out.println("cost is " + cost);

		EventType et = h.getEtype().get(request.getParameter("type"));
		System.out.println("request param type is " + request.getParameter("type"));
		int type = et.getId();

		int scale = h.getGscale().get(request.getParameter("gradingFormat"));
		String attachments = request.getParameter("fileinput");
		
		double reim = Double.parseDouble(cost) * et.getPercent();

		double left = edi.getRemainingBalance(id) - rdi.getPendingBalance(id);

		if (reim > left) {
			reim = left;
		}
		System.out.println(attachments);
		rdi.insertRequest(id, name, loc, date, desc, type, scale, pgrade, just, Double.parseDouble(cost), reim, attachments);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("home.html");
		rd.forward(request, response);
	}

}
