package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Cart_itemsDAO;

@WebServlet("/delete_item")
public class delete_item extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		int product_id = Integer.parseInt(request.getParameter("product"));
		String category = request.getParameter("category");
		
		Cart_itemsDAO cart_itemdao = new Cart_itemsDAO();
		
		cart_itemdao.delete(category, product_id, username);
		
		response.sendRedirect("cart.jsp");
	}

}
