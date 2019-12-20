package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

import DAO.BookDAO;
import DAO.CartDAO;
import DAO.Cart_itemsDAO;
import DAO.PaymentDAO;
import DAO.StationaryDAO;
import Others.Connect;

@WebServlet("/transaction")
public class transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    		HttpSession session = request.getSession();
    		String username = (String) session.getAttribute("username");
    		
    		
    		char[] otp = (char[]) session.getAttribute("otp");
    		String GenOTP = String.valueOf(otp);
    		String EnteredOTP = request.getParameter("EnteredOTP");

    		
    		if(GenOTP.equals(EnteredOTP)) {
    			
    			System.out.println("Go for transaction");
    			
    			session.removeAttribute("otp");
    			if(session.getAttribute("workshop_id")==null)
    			{
    			try {
    				
    				Connection myConn = null;
    				myConn = Connect.getConnection();
					myConn.setAutoCommit(false);
					System.out.println("aaaaa");
					int check4=-1;
					String sql1 = "select * from CART_ITEMS where cart_id = (select cart_id from CART where username=?)";
					PreparedStatement st1 = myConn.prepareStatement(sql1);
					st1.setString(1,username);
					ResultSet rs = st1.executeQuery();
					String seller = "";
					
					while(rs.next())
					{

						System.out.println("bbbbb");
						String category = rs.getString("category");
						
						if(category.equals("book"))
						{
							int id = rs.getInt("product_id");
							BookDAO bookdao = new BookDAO();
							try {
								seller = bookdao.getSeller(id);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String sql4 = "update BOOK set status=2 where id=?";
							PreparedStatement st4 = myConn.prepareStatement(sql4);
							st4.setInt(1,id);
							check4 = st4.executeUpdate();
						}
						
						else if(category.equals("stationary"))
						{

							System.out.println("ccccccc");
							int id = rs.getInt("product_id");
							StationaryDAO stationarydao = new StationaryDAO();
							try {
								seller = stationarydao.getSeller(id);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String sql4 = "update STATIONARY set status=2 where id=?";
							PreparedStatement st4 = myConn.prepareStatement(sql4);
							st4.setInt(1,id);
							check4 = st4.executeUpdate();

							System.out.println(check4);
						}
					}
					
					String sql7 = "insert into PAYMENT(cart_id,payment_amount,seller,payment_date) values(?,?,?,?)";
					PreparedStatement st7 = myConn.prepareStatement(sql7);
					CartDAO cartdao = new CartDAO();
					int cart_id = cartdao.GetCartId(username);
					Cart_itemsDAO cart_itemsdao = new Cart_itemsDAO();
					float amount = cart_itemsdao.GetTotalAmount(cart_id);
					java.util.Date utilDate = new Date();
					java.sql.Date currentdate = new java.sql.Date(utilDate.getTime());
					st7.setInt(1,cart_id);
					st7.setFloat(2,amount);
					st7.setString(3,seller);
					st7.setDate(4,currentdate);
					int check7 = st7.executeUpdate();
					System.out.println(check7);
					
					String sql = "select * from PAYMENT order by payment_id desc limit 1";
					PreparedStatement st = myConn.prepareStatement(sql);
					ResultSet r = st.executeQuery();
					int pay_id = -1;
					if(r.next())
					{
						System.out.println("I am in!");
						pay_id = r.getInt("payment_id");
					}
					
					System.out.println("pay_id");
					System.out.println(pay_id);
					
					
					String temp = "select * from CART_ITEMS where cart_id = (select cart_id from CART where username=?)";
					PreparedStatement s = myConn.prepareStatement(temp);
					s.setString(1,username);
					ResultSet res = s.executeQuery();
					
					while(res.next())
					{
						String sql10 = "insert into PAYMENT_ITEMS(payment_id, product_id, category, quantity, amount) values(?,?,?,?,?)";
						PreparedStatement st10 = myConn.prepareStatement(sql10);
						st10.setInt(1,pay_id);
						st10.setInt(2,res.getInt("product_id"));
						st10.setString(3,res.getString("category"));
						st10.setInt(4,res.getInt("quantity"));
						st10.setFloat(5,res.getFloat("amount"));
						st10.executeUpdate();
						System.out.println("check10");
					}
					
					
					String sql6 = "delete from CART_ITEMS where cart_id = (select cart_id from CART where username=?)";
					PreparedStatement st6 = myConn.prepareStatement(sql6);
					st6.setString(1,username);
					int check6 = st6.executeUpdate();
					System.out.println(check6);
					
					
					
					if(check4>=1 & check6>=1 & check7>=1)
	    			{

						System.out.println("dddddd");
	    				myConn.commit();
	    				System.out.println("Transaction was successful");
	    				session.setAttribute("msg","Transaction was successful!!!");
	    				response.sendRedirect("home.jsp");
	    			}
	    			else
	    			{

						System.out.println("ffffffffff");
	    				myConn.rollback();
	    				System.out.println("Transaction was unsuccessful");
	    				session.setAttribute("msg1","Transaction was unsuccessful!!!");
	    				response.sendRedirect("index.jsp");
	    			}
					
					
    			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			}
    			else	//WORKSHOP
    			{
    				System.out.println("i am in workshop");
    				int id = (Integer) session.getAttribute("workshop_id");
    				System.out.println(id);
    				session.removeAttribute("workshop_id");
    				System.out.println(id);
    				Connection myConn = null;
    				myConn = Connect.getConnection();
					try {
						myConn.setAutoCommit(false);
						int check2=-1, check3=-1;
						
						java.util.Date utilDate = new Date();
						java.sql.Date currentdate = new java.sql.Date(utilDate.getTime());
						
						
						String sql2 = "insert into PARTICIPATE values(?,?,?)";
						PreparedStatement st2 = myConn.prepareStatement(sql2);
						
						st2.setString(1,username);
						st2.setInt(2,id);
						st2.setDate(3,currentdate);
						check2 = st2.executeUpdate();
						
						String sql3 = "update WORKSHOP set vacant_seats = vacant_seats-1 where vacant_seats>0 and workshop_id=?";
						PreparedStatement st3 = myConn.prepareStatement(sql3);
						st3.setInt(1,id);
						check3 = st3.executeUpdate();
						
						if(check2>=1 & check3>=1)
						{
							System.out.println("dddddd");
		    				myConn.commit();
		    				System.out.println("Transaction was successful");
		    				session.setAttribute("msg","Thank You for registering!!!");
		    				response.sendRedirect("home.jsp");
						}
						else
		  
						
						
						{

							System.out.println("ffffffffff");
		    				myConn.rollback();
		    				System.out.println("Transaction was unsuccessful");
		    				session.setAttribute("msg1","Transaction was unsuccessful!!!");
		    				response.sendRedirect("index.jsp");
		    			}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
    				
    				
    			}
    			
    		}
    		
    		else
    		{
    			session.setAttribute("msg1","Please try again!");
    			response.sendRedirect("otp.jsp");
    		}
    		
    		
    	}
    		
}
					
					
					
					
				