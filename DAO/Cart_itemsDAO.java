package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Others.Connect;
import model.Cart_items;

public class Cart_itemsDAO {

	
	public boolean Insert(Cart_items cart)
	{
		String sql = "insert into CART_ITEMS values" + "(?,?,?,?,?)";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try
		{
			st = con.prepareStatement(sql);
			st.setInt(1,cart.getCart_id());
			st.setInt(2,cart.getProduct_id());
			st.setInt(3,cart.getQuantity());
			st.setFloat(4,cart.getAmount());
			st.setString(5,cart.getCategory());
			st.executeUpdate();
			
			return true;
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validate(String category, int product_id, String username)
	{
		String sql = "select * from CART_ITEMS where category=? and product_id=? and cart_id=(select cart_id from CART where username=?)";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try
		{
			st = con.prepareStatement(sql);
			st.setString(1,category);
			st.setInt(2,product_id);
			st.setString(3,username);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
			{
				return false;
			}
			
			return true;
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(String category, int product_id, String username)
	{
		String sql = "delete from CART_ITEMS where cart_id = (select cart_id from CART where username=?) and category=? and product_id=?";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try
		{
			st = con.prepareStatement(sql);
			st.setString(1,username);
			st.setString(2,category);
			st.setInt(3,product_id);
			st.executeUpdate();
			
			return true;
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}
	public float GetTotalAmount(int cart_id)
	{
		String sql = "select sum(amount) as total from CART_ITEMS where cart_id = ?";
		float amount=-1;
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try
		{
			st = con.prepareStatement(sql);
			st.setInt(1,cart_id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
			{
				amount = rs.getFloat("total");
			}
			
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return amount;
	}
	
	public boolean present(String username)
	{
		String sql = "select * from CART_ITEMS where cart_id = (select cart_id from CART where username=?)";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try {
			st = con.prepareStatement(sql);
			st.setString(1,username);
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
