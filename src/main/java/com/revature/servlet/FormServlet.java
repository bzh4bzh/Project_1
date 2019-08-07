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
		String name=request.getParameter("username");
		int id=edi.getUserID(name);
		
		name=request.getParameter("name");
		String loc= request.getParameter("location") ;
		
		String date= request.getParameter("date") ;
		String desc= request.getParameter("name") ;
		int type= Integer.parseInt(request.getParameter("type")) ;
		int gscale= Integer.parseInt(request.getParameter("gradingScale")) ;
		String pgrade= request.getParameter("passingGrade") ;
		String just= request.getParameter("justification") ;
		double cost= Double.parseDouble(request.getParameter("cost"));
		
		
//		double reim=cost*(edi.reimPercent(type))/100;
//				(edi.getRemainingBalance(id)-edi.getPendingBalance());
//		
//
//		RequestsDaoImipl rdi=new RequestsDaoImipl();
//		rdi.insertRequest(id, name, loc, date, desc, type, gscale, pgrade, just, cost, reim);
//		
		PrintWriter pw= response.getWriter();
//<<<<<<< HEAD
//		String rjson;
//		try {
//			//rjson=map.writeValueAsString();
//			//response.setContentType("application/json");
//			//response.setCharacterEncoding("UTF-8");
//			rdi.insertRequest(id)
//			pw.print(rjson);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//=======
//>>>>>>> f936f8996a33add5d93e448ab73e17d751f45721
		
		RequestDispatcher rd = request.getRequestDispatcher("home.html");
		rd.forward(request, response);
	}

}
