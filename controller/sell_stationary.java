package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.ProductDAO;
import DAO.StationaryDAO;
import model.Product;
import model.Stationary;

@WebServlet("/sell_stationary")
@MultipartConfig
public class sell_stationary extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Stationary sta = new Stationary();
		ProductDAO productdao = new ProductDAO();
			
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		String product_name = (String) request.getParameter("pid");
		
		if(product_name.equals("other")) {
			String new_category = (String) request.getParameter("newCategory");
			
			Product product = new Product();
			ProductDAO productdao1 = new ProductDAO();
			
			product.setCategory("stationary");
			product.setProduct_name(new_category);
	
			
			productdao1.Insert(product);
			
			
			int id = productdao.id(new_category);
			
			sta.setProduct_id(id);
		}
		else {
			int id = productdao.id(product_name);
			sta.setProduct_id(id);
		}
		
		Part filepart = request.getPart("image");
		
		sta.setS_name(request.getParameter("sname"));
		sta.setS_description(request.getParameter("sde"));
		sta.setPrice(Float.parseFloat((request.getParameter("price"))));
		sta.setSeller(username);
		sta.setS_image(filepart);
		
		StationaryDAO stationarydao = new StationaryDAO();
		boolean check1 = stationarydao.Insert(sta);
		
		if(check1)
		{
			session.setAttribute("msg", "Your product has been posted!!!");
			response.sendRedirect("sell_stationary.jsp");
		}
		else
		{

			session.setAttribute("msg1", "Sorry! Could not post your product. Please try again!");
			response.sendRedirect("home.jsp");
		}
		
	}
		
}