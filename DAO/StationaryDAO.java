package DAO;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.Part;

import Others.Connect;
import model.Stationary;

public class StationaryDAO {

	public boolean Insert(Stationary sta)
	{
		String sql = "insert into STATIONARY(product_id, s_name, s_description, price, seller, posted_on, s_image, status) values" + "(?,?,?,?,?,?,?,?)";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try
		{
			st = con.prepareStatement(sql);
			
			java.util.Date utilDate = new Date();
			java.sql.Date currentdate = new java.sql.Date(utilDate.getTime());
			
			Part filepart = sta.getS_image();
			InputStream inputstream =null; 
			inputstream = filepart.getInputStream();
			
			st.setInt(1,sta.getProduct_id());
			st.setString(2,sta.getS_name());
			st.setString(3,sta.getS_description());
			st.setFloat(4,sta.getPrice());
			st.setString(5,sta.getSeller());
			st.setDate(6,currentdate);
			st.setBlob(7,inputstream);
			st.setInt(8,1);
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
		String sql = "select * from STATIONARY where id=?";
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
				return true;	//true if still present for sell
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public float FeesByID(int id) throws SQLException, ClassNotFoundException {
	     
    	String sql = "SELECT price FROM STATIONARY where id=?";
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
	     
    	String sql = "SELECT seller FROM STATIONARY where id=?";
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
