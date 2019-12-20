package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Others.Connect;
import model.Login;

public class LoginDAO {

	public boolean check(String userid, String password)
	{
		String sql = "select * from LOGIN where login_id=? and login_password=?";
		
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		try {
			st = con.prepareStatement(sql);
			st.setString(1, userid);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean changePassword(String username, String oldPassword, String newPassword)
	{
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st=null, st1=null;
		String pass=null;
		
		String sql1 = "select login_password from LOGIN where login_id=(select login_id from HAS where username=?)";
		try {
			st1 = con.prepareStatement(sql1);
			st1.setString(1,username);
			ResultSet res = st1.executeQuery();
			if(res.next())
			{
				pass = res.getString("login_password");
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(pass.equals(oldPassword))
		{
		
			String sql = "update LOGIN set login_password=? where login_id=(select login_id from HAS where username=?)";
			
			
			try {
				st = con.prepareStatement(sql);
				st.setString(1, newPassword);
				st.setString(2, username);
				int rs = st.executeUpdate();
				if(rs>=1)
				{
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public String resetPassword(long mob_no, String email)
	{
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st=null, st1=null;
		String uname=null;
		String pass=null;
		
		String sql1 = "select * from USER where mob_no=? and email=?";
		try {
			st1 = con.prepareStatement(sql1);
			st1.setLong(1,mob_no);
			st1.setString(2,email);
			ResultSet res = st1.executeQuery();
			if(res.next())
			{
				uname = res.getString("username");
				String sql = "select login_password from LOGIN where login_id=(select login_id from HAS where username=?)";
				try {
					st = con.prepareStatement(sql);
					st.setString(1, uname);
					ResultSet rs = st.executeQuery();
					if(rs.next())
					{
						pass = rs.getString("login_password");
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return pass;
	}
	
	public boolean Insert(Login login)
	{
		String sql = "insert into LOGIN values" + "(?,?)";
		
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try
		{
			st = con.prepareStatement(sql);
			st.setString(1,login.getLogin_id());
			st.setString(2,login.getLogin_password());
			st.executeUpdate();
			
			return true;
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}
}
