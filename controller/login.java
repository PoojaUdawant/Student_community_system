package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.HasDAO;
import DAO.LoginDAO;


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		session.setAttribute("msg",null);
		session.setAttribute("msg1",null);
		session.setAttribute("msg3",null);
		
		LoginDAO logindao = new LoginDAO();
		HasDAO hasdao = new HasDAO();
		if(logindao.check(userid, password))
		{
			
			
			session.setAttribute("userid", userid);
			int role = hasdao.role(userid);
			session.setAttribute("role", role);
			String username = hasdao.username(userid);
			session.setAttribute("username", username);
			session.setAttribute("msg", "Successfully logged in!!!");
			if(role==1)
			{
				response.sendRedirect("home.jsp");
			}
			else if(role==2)
			{
				response.sendRedirect("home.jsp");
			}
			else if(role==3)
			{
				response.sendRedirect("admin_home.jsp");
			}
			else
			{
				response.sendRedirect("index.jsp");
			}
			
		}
		else
		{
			session.setAttribute("msg1", "Invalid login credentials!!!");
			response.sendRedirect("login.jsp");
		}
	}
}
