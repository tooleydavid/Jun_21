package com.ss.web.serverlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServerlet
 */
@WebServlet("/Welcome")
public class MyServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServerlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/Jun_2021_Web/index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String username = request.getParameter("username");
        String password =  request.getParameter("password");
        
        if("admin7".equals(username) && "password4".equals(password))
        {
        	response.sendRedirect("/Jun_2021_Web/loggedin.html");
        }
        else
        {
        		pw.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"ISO-8859-1\">"
				+ "<title>June 2021 Webapp</title>"
				+ "</head>"
				+ "<body>"
				+ "	<center>"
				+ "		<h1>June 2021 Webapp</h1>"
				+ "	</center>"
				+ "	<hr>"
				+ "	Log in failed<br>"
				+ " <a href=\"Welcome\">click here to try again</a>"
				+ ""
				+ "</body>"
				+ "</html>");
        }
		//response.sendRedirect("/Jun_2021_Web/loggedin.html");
	}

}
