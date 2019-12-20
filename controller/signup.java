package controller;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.CartDAO;
import DAO.HasDAO;
import DAO.LoginDAO;
import DAO.UserDAO;
import model.Cart;
import model.Has;
import model.Login;
import model.User;


@WebServlet("/signup")
@MultipartConfig
public class signup extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Part filepart = request.getPart("image");
		User user = new User();
		
		user.setUsername(request.getParameter("uname"));
		user.setFirst_name(request.getParameter("fname"));
		user.setLast_name(request.getParameter("lname"));
		user.setImage(filepart);
		
		long number = Long.parseLong(request.getParameter("contact"));
		user.setMob_no(number);
		user.setEmail(request.getParameter("email"));
		
		Login login = new Login();
		
		login.setLogin_id(request.getParameter("login_id"));
		login.setLogin_password(request.getParameter("password"));
		
		Has has = new Has();
		
		has.setLogin_id(request.getParameter("login_id"));
		has.setUsername(request.getParameter("uname"));
	
		Cart cart = new Cart();
		
		cart.setUsername(request.getParameter("uname"));
		cart.setTot_amt(0);
		
		UserDAO userdao = new UserDAO();
		LoginDAO logindao = new LoginDAO();
		HasDAO hasdao = new HasDAO();
		CartDAO cartdao = new CartDAO();
		
		boolean check5 = userdao.Check_username(request.getParameter("uname"));
		if(check5)
		{
		
		boolean check1 = userdao.Insert(user);
		boolean check2 = logindao.Insert(login);
		boolean check3 = hasdao.Insert(has);
		boolean check4 = cartdao.Insert(cart);
		
		
		
		
			if(check1 && check2 && check3 && check4)
			{
				HttpSession session = request.getSession();
				session.setAttribute("msg","Successfully Registered! Login for continuing...");
				response.sendRedirect("index.jsp");
			}
			else
			{
				HttpSession session = request.getSession();
				session.setAttribute("msg1","Sorry! Please try again!!!");
				response.sendRedirect("index.jsp");
			}
		}
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("msg1","Username is already taken!!! Please try again...");
			response.sendRedirect("login.jsp");
		}
	}

}

