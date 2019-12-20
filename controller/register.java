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

import DAO.BookDAO;
import DAO.Cart_itemsDAO;
import DAO.ParticipateDAO;
import DAO.StationaryDAO;
import DAO.WorkshopDAO;
import Others.Connect;
import model.Participate;


@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		int product_id = Integer.parseInt(request.getParameter("product"));
		String category = request.getParameter("category");
		boolean chk = false;
		
		session.setAttribute("workshop_id", product_id);
		Participate participate = new Participate();
		participate.setStudent(username);
		participate.setWorkshop_id(product_id);
		
		//workshop should not already be registered
		ParticipateDAO participatedao= new ParticipateDAO();
		boolean check1 = participatedao.check(participate);
		if(check1 == false)
			session.setAttribute("msg1", "Sorry! You have already registered for this workshop");
		
		//vacant seat should not be zero
		WorkshopDAO workshopdao = new WorkshopDAO();
		boolean check2=false;
		try {
			check2 = workshopdao.validate_seat(product_id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("check2");
		System.out.println(check2);
		if(check2 == false)
			session.setAttribute("msg1", "Sorry! No vacant seat available for this workshop");
		
		
		if(check1 && check2)
		{
			chk = true;
		}
		
		
		if(chk)
			response.sendRedirect("workshop_checkout.jsp");
		else
			response.sendRedirect("student_workshop.jsp");
	}

	
}
