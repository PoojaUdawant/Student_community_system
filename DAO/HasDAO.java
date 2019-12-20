package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Others.Connect;
import model.Has;

public class HasDAO {

	public int role(String userid)
	{
		String sql = "select * from HAS where login_id=?";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		int role = -1;
		
		try {
			st = con.prepareStatement(sql);
			st.setString(1, userid);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				role = rs.getInt("role_id");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return role;
	}
	
	public String username(String userid)
	{
		String sql = "select * from HAS where login_id=?";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		String username = null;
		
		try {
			st = con.prepareStatement(sql);
			st.setString(1, userid);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				username = rs.getString("username");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return username;
	}
	
	public boolean Insert(Has has)
	{
		String sql = "insert into HAS values" + "(?,?,?)";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try
		{
			st = con.prepareStatement(sql);
			st.setString(1,has.getLogin_id());
			st.setString(2,has.getUsername());
			st.setInt(3,1);
			st.executeUpdate();
			
			return true;
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}
}
