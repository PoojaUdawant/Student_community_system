package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AdminDAO;
import model.Workshop;
import model.admin_model;

/**
 * Servlet implementation class admin_search
 */
@WebServlet("/admin_search")
public class admin_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_search() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Workshop> listWorkshop = new ArrayList<>();

		String from = request.getParameter("from");
		String to = request.getParameter("to");
		 System.out.println(from);
		 System.out.println(to);
		try {
			listWorkshop = AdminDAO.searchWorkshop(from,to);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("listWorkshop", listWorkshop);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_workshopResults.jsp");
        dispatcher.forward(request, response);	}

}
