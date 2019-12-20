package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AdminDAO;
import model.Admin_login_model;

/**
 * Servlet implementation class admin_login
 */
@WebServlet("/admin_login")
public class admin_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Step 0:Add data
		String username,pass;
		username = request.getParameter("username");
		pass = request.getParameter("pass");
		
		Admin_login_model al = new Admin_login_model();
		al.setLogin_id(username);
		al.setLogin_password(pass);
		
		try {
			if(AdminDAO.check(al)==1)
			{
				HttpSession session = request.getSession();
				session.setAttribute("userid", username);
				
				//Step 1: get request dispatcher
				request.setAttribute("name", "shrey");
				RequestDispatcher dispatcher = request.getRequestDispatcher("admin_home.jsp");
				//Step 2: forward request to JSP
				dispatcher.forward(request, response);
			}
			else
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("admin_login.jsp");
				//Step 2: forward request to JSP
				dispatcher.forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		//Step 1: get request dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_home.jsp");
		//Step 2: forward request to JSP
		dispatcher.forward(request, response);
		*/
	}



}
