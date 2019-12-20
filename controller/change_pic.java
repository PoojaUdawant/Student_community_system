package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Others.Connect;

@WebServlet("/change_pic")
@MultipartConfig
public class change_pic extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part filepart = request.getPart("image");
		InputStream inputstream =null; 
		inputstream = filepart.getInputStream();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		String sql = "update USER set image=? where username=?";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try {
			st = con.prepareStatement(sql);
			st.setBlob(1,inputstream);
			st.setString(2,username);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("msg","Successfully updated profile photo!!!");
		response.sendRedirect("profile.jsp");
		
	}

}
