package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookDAO;
import DAO.CartDAO;
import DAO.Cart_itemsDAO;
import DAO.StationaryDAO;
import model.Cart_items;


@WebServlet("/add_to_cart")
public class add_to_cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
	
		int product_id = Integer.parseInt(request.getParameter("product"));
		String category = request.getParameter("category");
		
		CartDAO cartdao = new CartDAO();
		int cart_id = cartdao.GetCartId(username);
		
		float fees =0;
		
		
		if(category.equals("book"))
		{
			BookDAO bookdao = new BookDAO();
			
			try {
				fees = bookdao.FeesByID(product_id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(category.equals("stationary"))
		{
			StationaryDAO stationarydao = new StationaryDAO();
			
			try {
				fees = stationarydao.FeesByID(product_id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//CHECK IF ALREADY PRESENT IN CART
		Cart_itemsDAO cart_itemdao = new Cart_itemsDAO();
		boolean check = cart_itemdao.validate(category, product_id, username);
		
		System.out.println(check);
		
		if(check)
		{
			Cart_items cart = new Cart_items();
			cart.setCart_id(cart_id);
			cart.setProduct_id(product_id);
			cart.setQuantity(1);
			cart.setAmount(fees);
			cart.setCategory(category);
			
			Cart_itemsDAO cart_itemdao1 = new Cart_itemsDAO();
			boolean check1 = cart_itemdao1.Insert(cart);
		
		
			if(check1)
			{
				session.setAttribute("msg","Added to your cart!");
				response.sendRedirect("buy.jsp#start");	//ADDED TO CART
			}
			else
			{
				response.sendRedirect("home.jsp");		//NOT ADDED TO CART
			}
		}
		else {
			session.setAttribute("msg1","Already present in your Cart!");
			response.sendRedirect("cart.jsp");		//ALREADY PRESENT IN CART
		}
    	
	
	}
		
}
