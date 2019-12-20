package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.WorkshopDAO;
import model.Workshop;

@WebServlet("/workshop_description")
public class workshop_description extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));

		WorkshopDAO dao = new WorkshopDAO();
		Workshop workshop = new Workshop();
		
		try {
			workshop = dao.description(id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("workshop", workshop);
		request.getRequestDispatcher("student_workshop_description.jsp").forward(request, response);
		
	}

}
