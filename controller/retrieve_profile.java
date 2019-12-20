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
import javax.servlet.http.HttpSession;

import Others.Connect;

@WebServlet("/retrieve_profile")
public class retrieve_profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		con = Connect.getConnection();
		
		ResultSet rs = null;
		PreparedStatement st = null;
		String category = null;
		String sql = null;
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		

		sql = "SELECT image from USER where username=?";
		try {
			st = con.prepareStatement(sql);
			st.setString(1,username);
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
