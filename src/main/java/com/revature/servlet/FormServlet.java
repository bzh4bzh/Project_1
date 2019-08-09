package com.revature.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.EventType;
import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.RequestsDaoImipl;
import com.revature.util.ConnFactory;

/**
 * Servlet implementation class FormServlet
 */
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static HashMap<String, Integer> gscale = new HashMap<String, Integer>();
	//private static HashMap<String, EventType> etype = new HashMap<String, EventType>();
	{
		gscale.put("Letter", 1);
		gscale.put("Percentage", 2);
		gscale.put("Pass/Fail", 3);
		gscale.put("Other", 4);

		/*etype.put("University Courses", new EventType("University Courses", 0.8, 1));
		etype.put("Certification", new EventType("Certification", 1, 4));
		etype.put("Technical Training", new EventType("Technical Training", 0.9, 5));
		etype.put("Seminars", new EventType("Seminars", 0.6, 2));
		etype.put("Certification Preparation Classes", new EventType("Certification Preparation Classes", 0.75, 3));
		etype.put("Other", new EventType("Other", 0.3, 6));
		*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doPost of FormServlet");
		EmployeeDaoImpl edi=new EmployeeDaoImpl();
		RequestsDaoImipl rdi=new RequestsDaoImipl();
		String name=request.getParameter("username");
		System.out.println((String)request.getSession().getAttribute("name") +  " is the session username");
		int id=edi.getUserID((String)request.getSession().getAttribute("name"));
		
		System.out.println("user id is " + id + "for user " + name);
		name=request.getParameter("name");
		String loc= request.getParameter("location") ;
		String date= request.getParameter("date") ;
		String desc= request.getParameter("name") ;
		System.out.println("name is " + desc);
		String pgrade= request.getParameter("passingGrade") ;
		
		String just= request.getParameter("justification") ;
		System.out.println("just is " + just);
		String cost= request.getParameter("cost");
		System.out.println("cost is " + cost);
		
		EventType et = etype.get(request.getParameter("type"));
		System.out.println("request param type is " + request.getParameter("type"));
		int type = et.getId();

		int scale= gscale.get(request.getParameter("gradingFormat"));
		
		double reim=Double.parseDouble(cost)*et.getPercent();
		
		double left=edi.getRemainingBalance(id)-rdi.getPendingBalance(id);
		
		if (reim>left){
			reim=left;
		}
		rdi.insertRequest(id, name, loc, date, desc, type, scale, pgrade, just, Double.parseDouble(cost), reim);
				
		RequestDispatcher rd = request.getRequestDispatcher("home.html");
		rd.forward(request, response);
	}
	
	
	
	/************************************************************************************************/
	
	//APPROVE REQUEST
	protected void doPostApprove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doPost of ApproveServlet");
		EmployeeDaoImpl edi=new EmployeeDaoImpl();
		RequestsDaoImipl rdi=new RequestsDaoImipl();
		int id=edi.getUserID((String)request.getSession().getAttribute("name"));
		int recid=Integer.parseInt(request.getParameter("requestId"));
		rdi.updateStatus(edi.checkAthority(id),recid);
		
		RequestDispatcher rd = request.getRequestDispatcher("home.html");
		rd.forward(request, response);
	}
	//DENY REQUEST
	protected void doPostDeny(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doPost of DenyServlet");
		RequestsDaoImipl rdi=new RequestsDaoImipl();
		int recid=Integer.parseInt(request.getParameter("requestId"));
		rdi.updateStatus(-1,recid);
		
		RequestDispatcher rd = request.getRequestDispatcher("home.html");
		rd.forward(request, response);
	}
	
	

}
