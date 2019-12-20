package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.BookDAO;
import DAO.ProductDAO;
import model.Book;
import model.Product;


@WebServlet("/sell_book")
@MultipartConfig
public class sell_book extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = new Book();
		ProductDAO productdao = new ProductDAO();
	
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		String product_name = (String) request.getParameter("pid");
		
		if(product_name.equals("other")) {
			String new_category = (String) request.getParameter("newCategory");
			System.out.println(new_category);
			Product product = new Product();
			ProductDAO productdao1 = new ProductDAO();
			
			product.setCategory("book");
			product.setProduct_name(new_category);

			productdao1.Insert(product);
			
			int id = productdao.id(new_category);
			book.setProduct_id(id);
		}
		else {
			int id = productdao.id(product_name);
			book.setProduct_id(id);
		}
		Part filepart = request.getPart("image");
		
		book.setAuthor(request.getParameter("author"));
		book.setTitle(request.getParameter("title"));
		book.setPublisher(request.getParameter("publisher"));
		book.setVersion(request.getParameter("version"));
		book.setPublish_year(request.getParameter("date"));
		book.setPrice(Integer.parseInt((request.getParameter("price"))));
		book.setDescription(request.getParameter("description"));
		book.setSeller(username);
		book.setBook_image(filepart);
		
		
		BookDAO bookdao = new BookDAO();
		
		boolean check1 = bookdao.Insert(book);
		
		
		if(check1)
		{
			session.setAttribute("msg", "Your product has been posted!!!");
			response.sendRedirect("sell_book.jsp");
		}
		else
		{
			session.setAttribute("msg1", "Sorry! Could not post your product. Please try again!");
			response.sendRedirect("home.jsp");
		}
	}
	}
