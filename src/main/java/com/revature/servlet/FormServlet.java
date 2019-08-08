package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.RequestsDaoImipl;

/**
 * Servlet implementation class FormServlet
 */
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		
		/*
		 * so i dont know how to get the options to come with a name field
		 * i'm pretty sure picking the option will return the option in the parameter
		 * if i cant get it to return a number then we'll have to find the id by the string
		 * which would be 
		 * 		SELECT eventid from eventtype where eventtype=?
		 * the same issue occurs for grading scale with basically the same solution
		 * 		SELECT gradeid from gadingscale where gradename=?
		 */
		//potential solutions:
		//		etypedaoimpl etdi= new etypedaoimpl();
		//		int type= etdi.getTypeID(request.getParameter("type"));
		int type= Integer.parseInt(request.getParameter("type")) ;
		//		gscaledaoimpl gsdi= new gscaledaoimpl();
		//		int gscale = gsdi.getGradeId(request.getParameter("gradingScale"));
		int gscale= Integer.parseInt(request.getParameter("gradingScale")) ;
		
		//reimPercent should be Select reimbursepercent from eventType where eventid=type;
		double reim=cost*(edi.reimPercent(type))/100;
		/*
		 * getRemainingBalance is SELECT remainingBal from employee where userid=id
		 * getPendingBalance is SELECT sum(reimbursment) from request where userid=?;
		 */
		double left=edi.getRemainingBalance(id)-rdi.getPendingBalance(id);
		//if the reimbursement is > the amount left to be reimbursed then the program will 
		//only offer the amount left
		if (reim>left){
			reim=left;
		}

		rdi.insertRequest(id, name, loc, date, desc, type, gscale, pgrade, just, cost, reim);
		
		PrintWriter pw= response.getWriter();
		
		RequestDispatcher rd = request.getRequestDispatcher("home.html");
		rd.forward(request, response);
	}

}
