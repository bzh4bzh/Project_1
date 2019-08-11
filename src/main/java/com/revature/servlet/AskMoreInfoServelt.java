package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.daoimpl.RequestsDaoImipl;

/**
 * Servlet implementation class AskMoreInfoServelt
 */
public class AskMoreInfoServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doPost of AskMoreInfoServlet");
		if(request.getSession(false)==null) {
			response.sendRedirect("login.html");
		}
		RequestsDaoImipl rdi=new RequestsDaoImipl();
		System.out.println(request.getParameter("recind"));
		int recid=Integer.parseInt(request.getParameter("recind"));
		String basic = request.getParameter("basicEmployee");
		String sup = request.getParameter("DirectSuper");
		String head = request.getParameter("DepartmentHead");
		int auth = -1;
		if(basic != null) {
			auth = 0;
		}else if(sup != null) {
			auth = 1;
		}else if(head != null) {
			auth = 2;
		}
		rdi.updateFlagged(recid, auth);
		
		response.sendRedirect("supHomeAsk.html");
	}

}
