package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.RequestsDaoImipl;

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
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		RequestsDaoImipl rdi = new RequestsDaoImipl();
		System.out.println("In doPost of LoginServlet");
		PrintWriter out = response.getWriter();
		// request.getRequestDispatcher("link.html").include(request, response);

		String name = request.getParameter("username");
		name = (name == null) ? "" : name;
		//System.out.println("username is |" + name);
		String password = request.getParameter("password");
		password = (password == null) ? "" : password;
		//System.out.println(edi.authenticate(name));
		if (edi.authenticate(name) == null) {
			request.getRequestDispatcher("invalidCredentials.html").forward(request, response);
			out.print("<h1>sorry, username or password error!</h1>"); // its just password error
		} else if (edi.authenticate(name).equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("name", name); // this gives the session a key value pair
			int auth = edi.getUser(edi.getUserID(name)).getAuthority();
			if (auth == -1) {
				request.getRequestDispatcher("invalidCredentials.html").forward(request, response);
				out.print("<h1>sorry, username or password error!</h1>"); // its just password error
			} else if (auth >= 1) {
				//prints out pending request for supervisor 
				String supName = (String)request.getSession().getAttribute("name");
				System.out.println("Supervisor name is:" + supName);
				int supID = edi.getUserID(supName);
				System.out.println("Supervisor id is " + supID);
				System.out.println(rdi.getPendingSuper(supID));
				out.print(rdi.getPendingSuper(edi.getUserID((String)request.getSession().getAttribute("name"))));
				//request.getRequestDispatcher("table.html").forward(request, response);
			} else {
				request.getRequestDispatcher("home.html").forward(request, response);
			}
		} else {
			// response.sendRedirect("home");
			request.getRequestDispatcher("invalidCredentials.html").forward(request, response);
			out.print("<h1>sorry, username or password error!</h1>"); // its just password error
		}
	}

}
