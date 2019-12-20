package controller;

import java.io.IOException;
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

import Others.Connect;

@WebServlet("/request_coordinator")
public class request_coordinator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String purpose = request.getParameter("purpose");
		String sql = "insert into REQUEST(student_name, purpose) values" + "(?,?)";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		try {
			st = con.prepareStatement(sql);
			st.setString(1,username);
			st.setString(2,purpose);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("msg","Your request has been sent successfully!!!");
		response.sendRedirect("home.jsp");
	}

}
