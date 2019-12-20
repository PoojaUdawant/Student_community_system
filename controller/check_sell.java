package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/check_sell")
public class check_sell extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String category = request.getParameter("product");
		
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		if(username==null)
		{
			response.sendRedirect("login.jsp");
			session.setAttribute("msg1", "Please login for continuing....");
		}
		
		else
		{
			request.setAttribute("product",category);
			
			RequestDispatcher rd = request.getRequestDispatcher("sell_redirect");
			rd.forward(request,response);
			
		}
		
		
	}


}
