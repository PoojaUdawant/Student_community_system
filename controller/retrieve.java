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

@WebServlet("/retrieve")
public class retrieve extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		con = Connect.getConnection();
		
		ResultSet rs = null;
		PreparedStatement st = null;
		String sql = "SELECT logo from WORKSHOP where workshop_id=?";
		
		String j=request.getParameter("id");
		int i=Integer.parseInt(j);
		System.out.println(i);
		
		try {
			st = con.prepareStatement(sql);
			st.setInt(1,i);
			
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
