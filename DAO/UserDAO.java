package DAO; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Part;

import Others.Connect;
import model.User;
public class UserDAO
{
		public boolean Insert(User user)
		{
			String sql = "insert into USER(username, first_name, last_name, mob_no, email, image) values" + "(?,?,?,?,?,?)";
			Connection con = null;
			con = Connect.getConnection();
			PreparedStatement st;
			
			Part filepart = user.getImage();
			InputStream inputstream =null; 
			
			
			try {
				inputstream = filepart.getInputStream();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try
			{
				st = con.prepareStatement(sql);
				st.setString(1,user.getUsername());
				st.setString(2,user.getFirst_name());
				st.setString(3,user.getLast_name());
				st.setLong(4,user.getMob_no());
				st.setString(5,user.getEmail());
				st.setBlob(6,inputstream);
				st.executeUpdate();
				
				return true;
			} catch (Exception e) 
			{
				
				e.printStackTrace();
			}
			return false;
		}
		
		public boolean changeMobile(String username, long oldMobile, long newMobile)
		{
			System.out.println("i m in dao");
			Connection con = null;
			con = Connect.getConnection();
			PreparedStatement st=null, st1=null;
			long pass=-1;
			
			String sql1 = "select mob_no from USER where username=?";
			try {
				st1 = con.prepareStatement(sql1);
				st1.setString(1,username);
				ResultSet res = st1.executeQuery();
				if(res.next())
				{
					pass = res.getLong("mob_no");
				}
				System.out.println(pass);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(pass == oldMobile)
			{
				System.out.println("i m updating");
				String sql = "update USER set mob_no=? where username=?";
				
				
				try {
					st = con.prepareStatement(sql);
					st.setLong(1, newMobile);
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
		
		
		public boolean changeEmail(String username, String oldEmail, String newEmail)
		{
			System.out.println("i m in dao");
			Connection con = null;
			con = Connect.getConnection();
			PreparedStatement st=null, st1=null;
			String pass= null;
			
			String sql1 = "select email from USER where username=?";
			try {
				st1 = con.prepareStatement(sql1);
				st1.setString(1,username);
				ResultSet res = st1.executeQuery();
				if(res.next())
				{
					pass = res.getString("email");
				}
				System.out.println(pass);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(pass.equals(oldEmail))
			{
				System.out.println("i m updating");
				String sql = "update USER set email=? where username=?";
				
				
				try {
					st = con.prepareStatement(sql);
					st.setString(1, newEmail);
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
		
		public boolean Check_username(String username)
		{
			String sql = "select * from USER where username=?";
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
						return false;		//username already exist
				}
				
				return true;
			} catch (Exception e) 
			{
				
				e.printStackTrace();
			}
			return true;
		}
		
		
	}