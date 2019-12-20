package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AdminDAO;
import model.admin_model;

/**
 * Servlet implementation class admin_create
 */
@WebServlet("/admin_create")
public class admin_create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_create() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		admin_model md=new admin_model();
		md.setUsername(request.getParameter("username"));
		md.setFirstname(request.getParameter("firstname"));
		md.setLastname(request.getParameter("lastname"));
		md.setEmail(request.getParameter("email"));
		md.setMob_no(request.getParameter("mob_no"));
		String from,to;
	  
	/*	
		HttpSession session = request.getSession(true);
	     String id = (String) session.getAttribute("userid");
	     System.out.println(id);
	     try {
			AdminDAO.getuserEmail(id);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	*/
	     try {
			AdminDAO.insert(md);
		
				from = "";
				to = md.getEmail();
				System.out.println(to);
				AdminDAO.sendmail(from, to);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

		response.sendRedirect("admin_view.jsp");

	}
/*

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
*/
 
}
