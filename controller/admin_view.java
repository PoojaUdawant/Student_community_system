package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AdminDAO;
import model.admin_model;

/**
 * Servlet implementation class admin_view
 */
@WebServlet("/admin_view")
public class admin_view extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_view() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			List<admin_model> listAdmin;
			try {
				listAdmin = AdminDAO.listAllAdmin();
				request.setAttribute("listAdmin",listAdmin);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_view.jsp");
        dispatcher.forward(request, response);	
        }
	}


