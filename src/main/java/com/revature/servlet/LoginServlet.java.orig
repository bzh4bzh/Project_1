package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In doGet of LoginServlet");
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In doPost of LoginServlet");
		PrintWriter out = response.getWriter();
		//request.getRequestDispatcher("link.html").include(request, response);
		
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(password.equals("admin123")) {
			out.print("Welcome, " + name);
			out.print("your password is" + password);
			HttpSession session = request.getSession();
			session.setAttribute("name", name); 	// this gives the session a key value pair
		} else {
		// response.sendRedirect("home");
		request.getRequestDispatcher("login.html").forward(request, response);
		out.print("<h1>sorry, username or password error!</h1>"); // its just password error
		}
	}

}
