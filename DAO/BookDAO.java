package DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.http.Part;

import Others.Connect;
import model.Book;
public class BookDAO {
	
	public boolean Insert(Book book)
	{
		String sql = "insert into BOOK(product_id, author, title, publisher, version, publish_year, price, description, seller, posted_on, book_image, status) values" + "(?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try
		{
			
			st = con.prepareStatement(sql);
			System.out.println("hello");
			java.util.Date utilDate = new Date();
			java.sql.Date currentdate = new java.sql.Date(utilDate.getTime());
			
			Part filepart = book.getBook_image();
			InputStream inputstream =null; 
			
			
			inputstream = filepart.getInputStream();
			
			st.setInt(1,book.getProduct_id());
			st.setString(2,book.getAuthor());
			st.setString(3,book.getTitle());
			st.setString(4,book.getPublisher());
			st.setString(5,book.getVersion());
			st.setString(6,book.getPublish_year());
			st.setFloat(7,book.getPrice());
			st.setString(8,book.getDescription());
			st.setString(9,book.getSeller());
			st.setDate(10,currentdate);
			st.setBlob(11, inputstream);
			st.setInt(12,1);
			
			st.executeUpdate();
			
			return true;
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}

	public boolean present(int id)
	{
		String sql = "select * from BOOK where id=?";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		int status=0;
		
		try {
			st = con.prepareStatement(sql);
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				status = rs.getInt("status");
			}
			
			if(status == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public float FeesByID(int id) throws SQLException, ClassNotFoundException {
 	     
    	String sql = "SELECT price FROM BOOK where id=?";
    	Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
        try {
        	st = con.prepareStatement(sql);
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
			{
				return(rs.getFloat("price"));
				
			}
			
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return -1;
    }
	
	public String getSeller(int id) throws SQLException, ClassNotFoundException {
	     
    	String sql = "SELECT seller FROM BOOK where id=?";
    	Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
        try {
        	st = con.prepareStatement(sql);
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
			{
				return(rs.getString("seller"));
				
			}
			
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return null;
    }
	
	
}
