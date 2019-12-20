package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.LoginDAO;
import DAO.UserDAO;

@WebServlet("/change_mobile")
public class change_mobile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("i m in servlet");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		long oldMobile = Long.parseLong(request.getParameter("currentPassword"));
		long newMobile = Long.parseLong(request.getParameter("newPassword"));
		
		UserDAO userdao = new UserDAO();
		System.out.println(username);
		System.out.println(oldMobile);
		System.out.println(newMobile);
		
		boolean check = userdao.changeMobile(username, oldMobile, newMobile);
		
		if(check == true)
		{
			session.setAttribute("msg","Mobile Number changed successfuly!!!");
			response.sendRedirect("home.jsp");
		}
		else
		{
			session.setAttribute("msg1","Sorry! Please try again!!!");
			response.sendRedirect("home.jsp");
		}
	}

	
}
