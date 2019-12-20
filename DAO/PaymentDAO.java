package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import Others.Connect;

public class PaymentDAO {
	
	public int GetID(int cart_id) throws SQLException, ClassNotFoundException {
 	     
    	String sql = "SELECT * from PAYMENT where payment_date=? and cart_id=? order by payment_id desc limit 1";
    	Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		java.util.Date utilDate = new Date();
		java.sql.Date currentdate = new java.sql.Date(utilDate.getTime());
		
        try {
        	st = con.prepareStatement(sql);
			st.setDate(1,currentdate);
			st.setInt(1,cart_id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
			{
				return(rs.getInt("payment_id"));
				
			}
			
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return -1;
    }
    
}

