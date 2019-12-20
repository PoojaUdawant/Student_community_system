package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import Others.Connect;
import model.Participate;

public class ParticipateDAO {

	public boolean Insert(Participate participate)
	{
		String sql = "insert into PARTICIPATE values" + "(?,?,?)";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		java.util.Date utilDate = new Date();
		java.sql.Date currentdate = new java.sql.Date(utilDate.getTime());
		
		try
		{
			st = con.prepareStatement(sql);
			st.setString(1,participate.getStudent());
			st.setInt(2,participate.getWorkshop_id());
			st.setDate(3,currentdate);
			st.executeUpdate();
			
			return true;
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean check(Participate participate)
	{
		String sql = "select * from PARTICIPATE where student=? and workshop_id=?";
		Connection con = null;
		con = Connect.getConnection();
		PreparedStatement st;
		
		try
		{
			st = con.prepareStatement(sql);
			st.setString(1,participate.getStudent());
			st.setInt(2,participate.getWorkshop_id());
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
			{
				return false;	//return false if student has already registered
			}
			
			return true;		//if true then can go for registeration
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}
}
