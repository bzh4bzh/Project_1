package com.revature.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.EventType;
import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.RequestsDaoImipl;

/**
 * Servlet implementation class FormServlet
 */
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static HashMap<String, Integer> gscale = new HashMap<String, Integer>();
	private static HashMap<String, EventType> etype = new HashMap<String, EventType>();
	{
		gscale.put("Letter", 1);
		gscale.put("Percentage", 2);
		gscale.put("Pass/Fail", 3);
		gscale.put("Other", 4);

		etype.put("University Courses", new EventType("University Courses", 0.8, 1));
		etype.put("Certification", new EventType("Certification", 1, 4));
		etype.put("Technical Training", new EventType("Technical Training", 0.9, 5));
		etype.put("Seminars", new EventType("Seminars", 0.6, 2));
		etype.put("Certification Preparation Classes", new EventType("Certification Preparation Classes", 0.75, 3));
		etype.put("Other", new EventType("Other", 0.3, 6));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doGet of FormServlet");
		EmployeeDaoImpl edi=new EmployeeDaoImpl();
		RequestsDaoImipl rdi=new RequestsDaoImipl();
		String name=request.getParameter("username");
		int id=edi.getUserID(name);
		
		name=request.getParameter("name");
		String loc= request.getParameter("location") ;
		String date= request.getParameter("date") ;
		String desc= request.getParameter("name") ;
		String pgrade= request.getParameter("passingGrade") ;
		String just= request.getParameter("justification") ;
		double cost= Double.parseDouble(request.getParameter("cost"));
		
		
		EventType et = etype.get(request.getParameter("type"));
		int type = et.getId();

		int scale= gscale.get(request.getParameter("gradingScale"));
		
		double reim=cost*et.getPercent();
		
		double left=edi.getRemainingBalance(id)-rdi.getPendingBalance(id);
		
		if (reim>left){
			reim=left;
		}
		rdi.insertRequest(id, name, loc, date, desc, type, scale, pgrade, just, cost, reim);
				
		RequestDispatcher rd = request.getRequestDispatcher("home.html");
		rd.forward(request, response);
	}

}
