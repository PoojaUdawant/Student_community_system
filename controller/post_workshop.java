package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.WorkshopDAO;
import model.Workshop;

@WebServlet("/post_workshop")
@MultipartConfig
public class post_workshop extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Workshop workshop = new Workshop();
		System.out.println("I am in servlet");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		
    	workshop.setTitle(request.getParameter("title"));
		workshop.setConducted_by(request.getParameter("conducted_by"));
		workshop.setW_start_date(request.getParameter("w_start_date"));
		workshop.setW_end_date(request.getParameter("w_end_date"));
		workshop.setDuration(Integer.parseInt(request.getParameter("duration")));
		workshop.setTot_seats(Integer.parseInt(request.getParameter("tot_seats")));
		workshop.setFees(Float.parseFloat(request.getParameter("fees")));
		workshop.setPosted_by(username);
		workshop.setPdf(request.getPart("pdf"));
		
		WorkshopDAO dao = new WorkshopDAO();
		boolean check = dao.Insert(workshop);
		
		System.out.println(check);
		
		if(check)
		{
			session.setAttribute("msg", "Workshop has been posted successfully!!!");
			response.sendRedirect("post_workshop.jsp");
		}
		else
		{
			session.setAttribute("msg1", "Sorry! Could not post your workshop. Please try again!");
			response.sendRedirect("home.jsp");
		}
		
	}
}
