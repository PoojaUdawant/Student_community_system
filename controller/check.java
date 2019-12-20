package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/check")
public class check extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		int product_id = Integer.parseInt(request.getParameter("product"));
		String category = request.getParameter("category");
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		if(username==null)
		{
			response.sendRedirect("login.jsp");
			session.setAttribute("msg1", "Please login for continuing....");
		}
		
		else
		{
			request.setAttribute("workshop_id",product_id);
			request.setAttribute("category",category);
			if(category.equals("workshop"))
			{
				RequestDispatcher rd = request.getRequestDispatcher("register");
				rd.forward(request,response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("add_to_cart");
				rd.forward(request,response);
			}
		}
	}


}
