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
 * Servlet implementation class supHome
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession(false) == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.forward(request, response);
		}
		
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		RequestsDaoImipl rdi = new RequestsDaoImipl();
		System.out.println("In doPost of HomeServlet");
		PrintWriter out = response.getWriter();
		// request.getRequestDispatcher("link.html").include(request, response);

		String name = (String)request.getSession().getAttribute("name");
		int auth = edi.getUser(edi.getUserID(name)).getAuthority();
		
		if (auth == 1) {
			//prints out pending request for supervisor 
			String supName = (String)request.getSession().getAttribute("name");
			System.out.println("Supervisor name is:" + supName);
			//int supID = edi.getUserID(supName);
			//System.out.println("Supervisor id is " + supID);
			//System.out.println(rdi.getPendingSuper(supID));
			out.print(rdi.getPendingSuper(edi.getUserID((String)request.getSession().getAttribute("name"))));
			//request.getRequestDispatcher("supHome.html").forward(request, response);
		} else if(auth == 2) {
			out.print(rdi.getPendingDeptHead(edi.getDeptID((String)request.getSession().getAttribute("name"))));
		} else if(auth == 3) {
			out.print(rdi.getPendingBenCo());
		}else {
			request.getRequestDispatcher("login.html").forward(request, response);
		}
	}

}
