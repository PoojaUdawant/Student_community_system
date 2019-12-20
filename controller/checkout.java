 package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookDAO;
import DAO.Cart_itemsDAO;
import DAO.ParticipateDAO;
import DAO.StationaryDAO;
import DAO.WorkshopDAO;
import Others.Connect;
import model.Participate;


@WebServlet("/checkout")
public class checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		
		Cart_itemsDAO cart_itemdao = new Cart_itemsDAO();
		boolean check = cart_itemdao.present(username);
		
		
		String sql = "select * from CART_ITEMS where cart_id = (select cart_id from CART where username=?)";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		boolean chk = false;
		
		try
		{
			st = con.prepareStatement(sql);
			st.setString(1,username);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				String category = rs.getString("category");
					
				if(category.equals("book"))
				{
					//validate if product still available for selling
					BookDAO bookdao = new BookDAO();
					chk = bookdao.present(rs.getInt("product_id"));
					if(chk == false)	//BOOK SOLD OUT
					{
						session.setAttribute("msg1","Sorry! The product is sold out.");
						Cart_itemsDAO cart_itemsdao = new Cart_itemsDAO();
						cart_itemsdao.delete(category, rs.getInt("product_id"), username);
					}
				
				}
				
				else if(category.equals("stationary"))
				{
					//validate if product still available for selling
					StationaryDAO stationarydao = new StationaryDAO();
					chk = stationarydao.present(rs.getInt("product_id"));
					if(chk == false)	//STATIONARY SOLD OUT
					{
						session.setAttribute("msg1","Sorry! The product is sold out.");
						Cart_itemsDAO cart_itemsdao = new Cart_itemsDAO();
						cart_itemsdao.delete(category, rs.getInt("product_id"), username);
					}
				}
			}
			
			
			
			
			
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
		
		
		if(check && chk)
		{
			response.sendRedirect("checkout.jsp");
		}
		else
		{
			response.sendRedirect("cart.jsp");
		}
	}

	
}
