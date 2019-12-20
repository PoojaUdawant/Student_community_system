package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Others.Connect;
import model.Product;

public class ProductDAO {

	public int id(String product_name)
	{
		String sql = "select * from PRODUCT where product_name=?";
		int id = -1;
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		
		try {
			st = con.prepareStatement(sql);
			st.setString(1, product_name);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				id = rs.getInt("product_id");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return id;
	}
	
	public boolean Insert(Product product)
	{
		String sql = "insert into PRODUCT(product_name, category) values" + "(?,?)";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try
		{
			st = con.prepareStatement(sql);
			st.setString(1,product.getProduct_name());
			st.setString(2,product.getCategory());
			st.executeUpdate();
			
			return true;
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}
	
}
