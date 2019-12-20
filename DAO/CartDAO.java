package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Others.Connect;
import model.Cart;

public class CartDAO {

	
	public boolean Insert(Cart cart)
	{
		String sql = "insert into CART(username,tot_amt) values" + "(?,?)";
		
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try
		{
			st = con.prepareStatement(sql);
			st.setString(1,cart.getUsername());
			st.setFloat(2,cart.getTot_amt());
			st.executeUpdate();
			
			return true;
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}
	
	public int GetCartId(String username)
	{
		String sql = "select * from CART where username=?";
		
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try
		{
			st = con.prepareStatement(sql);
			st.setString(1,username);
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				return rs.getInt("cart_id");
			}

		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return -1;
	}
	
	
}
