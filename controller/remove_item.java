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

@WebServlet("/remove_item")
public class remove_item extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		String sql = "select category from PRODUCT where product_id=?";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		String category=null;
		
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, pid);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				category = rs.getString("category");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		
		if(category.equals("book"))
		{
			String sql1 = "delete from BOOK where id=? and product_id=?";
			PreparedStatement st1;
			try {
				st1 = con.prepareStatement(sql1);
				st1.setInt(1, id);
				st1.setInt(2, pid);
				int rs1 = st1.executeUpdate();
				if(rs1 >=1)
				{
					session.setAttribute("msg","Deleted your product successsfully!!!");
				}
				else
				{
					session.setAttribute("msg1","Sorry! Please try again...");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			String sql1 = "delete from STATIONARY where id=? and product_id=?";
			PreparedStatement st1;
			try {
				st1 = con.prepareStatement(sql1);
				st1.setInt(1, id);
				st1.setInt(2, pid);
				int rs1 = st1.executeUpdate();
				if(rs1 >=1)
				{
					session.setAttribute("msg","Deleted your product successsfully!!!");
				}
				else
				{
					session.setAttribute("msg1","Sorry! Please try again...");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("myOrders.jsp");
	}

}
