package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Others.Connect;
import model.Payment_items;
import model.Workshop;



@WebServlet("/payment_details")
public class payment_details extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));

		String sql = "SELECT * FROM PAYMENT_ITEMS where payment_id=?";
		
		System.out.println(id);
        Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		List<Payment_items> listPayment = new ArrayList<>();
		
		
        try {
        	
        	
        	st = con.prepareStatement(sql);
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				Payment_items payment = new Payment_items();
				System.out.println("aaaaaaaaaaaaaaa");
				payment.setPayment_id(rs.getInt("payment_id"));
				payment.setProduct_id(rs.getInt("product_id"));
				payment.setQuantity(1);
				payment.setAmount(rs.getFloat("amount"));
				payment.setCategory(rs.getString("category"));
				
				listPayment.add(payment);
			}
			
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
				throw ex;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }      
         
        request.setAttribute("listPayment",listPayment);
        request.getRequestDispatcher("payment_details.jsp").forward(request, response);
	}

}
