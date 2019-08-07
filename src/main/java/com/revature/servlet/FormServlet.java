package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.RequestsDaoImipl;

/**
 * Servlet implementation class FormServlet
 */
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doGet of FormServlet");
		ObjectMapper map=new ObjectMapper();
		EmployeeDaoImpl edi=new EmployeeDaoImpl();
		String name=map.readValue(request.getParameter("username"), String.class);
		int id=edi.getUserID(name);
		

		RequestsDaoImipl rdi=new RequestsDaoImipl();
		
		PrintWriter pw= response.getWriter();
		String rjson;
		try {
			//rjson=map.writeValueAsString();
			//response.setContentType("application/json");
			//response.setCharacterEncoding("UTF-8");
			rdi.insertRequest(id)
			pw.print(rjson);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("home.html");
		rd.forward(request, response);
	}

}
