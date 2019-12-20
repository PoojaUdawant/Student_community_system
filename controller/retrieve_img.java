package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Others.Connect;

@WebServlet("/retrieve_img")
public class retrieve_img extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		con = Connect.getConnection();
		
		ResultSet rs = null;
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		String category = null;
		String sql = null;

		int id = Integer.parseInt(request.getParameter("id"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		System.out.println(id);
		System.out.println(pid);
		
		String temp = "select category from PRODUCT where product_id=?";
		try {
			st1 = con.prepareStatement(temp);
			st1.setInt(1,pid);
			rs = st1.executeQuery();
			if(rs.next()) {
				category = rs.getString("category");
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(category);
		if(category.equals("book"))
			sql = "SELECT book_image from BOOK where id=?";
		else
			sql = "SELECT s_image from STATIONARY where id=?";
		
		
		try {
			st = con.prepareStatement(sql);
			st.setInt(1,id);
			
		    rs = st.executeQuery();
		    OutputStream o = response.getOutputStream();
		    if(rs.next())
		    {
		             Blob bl = rs.getBlob(1);
		             byte[] pic = bl.getBytes(1,(int)bl.length());
		             response.setContentType("image/jpg");
		             o.write(pic);
		             o.flush();
		             o.close();


		    }

		}
		catch(Exception ex)
		{
			System.out.println(ex);
		} 
		finally 
		{
		    try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
