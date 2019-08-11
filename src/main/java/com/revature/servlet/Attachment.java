package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Attachment
 */
public class Attachment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In doPost ins AttachmentServlet");
		String file = request.getParameter("file"); // Retrieves <input type="text" name="description">
	    //Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
	    RequestDispatcher rd = request.getRequestDispatcher("home.html");
	    rd.forward(request, response);
	}

}
