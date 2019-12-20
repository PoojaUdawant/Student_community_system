package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.LoginDAO;

@WebServlet("/Change_password")
public class Change_password extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String newpass = (String) request.getParameter("newPassword");
		String oldpass = (String) request.getParameter("currentPassword");
		
		LoginDAO logindao = new LoginDAO();
		
		boolean check = logindao.changePassword(username, oldpass, newpass);
		
		if(check == true)
		{
			session.setAttribute("msg","Password changed successfully!!!");
			response.sendRedirect("home.jsp");
		}
		else
		{
			session.setAttribute("msg1","Sorry! Please try again!!!");
			response.sendRedirect("home.jsp");
		}
	}

	
}
