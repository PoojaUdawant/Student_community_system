package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Others.Connect;

@WebServlet("/recieve_item")
public class recieve_item extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String category = request.getParameter("category");
		
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		if(category.equals("book"))
		{
			String sql = "update BOOK set status=0 where id=?";
			try {
				st = con.prepareStatement(sql);
				st.setInt(1, id);
				int rs = st.executeUpdate();
				if(rs>=1)
				{
					session.setAttribute("msg","Thank You for shopping with us!!!");
				}
				else
				{
					session.setAttribute("msg1","Sorry! Please try again!!!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(category.equals("stationary"))
		{
			String sql = "update STATIONARY set status=0 where id=?";
			try {
				st = con.prepareStatement(sql);
				st.setInt(1, id);
				int rs = st.executeUpdate();
				if(rs>=1)
				{
					session.setAttribute("msg","Thank You for shopping with us!!!");
				}
				else
				{
					session.setAttribute("msg1","Sorry! Please try again!!!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		response.sendRedirect("myOrders.jsp");
	}

	
}
