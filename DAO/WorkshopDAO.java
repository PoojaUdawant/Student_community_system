package DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.Part;

import Others.Connect;
import model.Workshop;


public class WorkshopDAO {
	     
	    public Workshop description(int id) throws SQLException, ClassNotFoundException {
	        
	        String sql = "SELECT * FROM WORKSHOP where workshop_id=?";
	        Connection con = null;
			con = Connect.getConnection();
			PreparedStatement st;
			Workshop workshop = new Workshop();
			
	        try {
	        	
	        	
	        	st = con.prepareStatement(sql);
				st.setInt(1,id);
				ResultSet rs = st.executeQuery();
				
				if(rs.next())
				{
					
					workshop.setWorkshop_id(rs.getInt("workshop_id"));
					workshop.setTitle(rs.getString("title"));
					workshop.setConducted_by(rs.getString("conducted_by"));
					workshop.setW_start_date(rs.getString("w_start_date"));
					workshop.setW_end_date(rs.getString("w_end_date"));
					workshop.setDuration(rs.getInt("duration"));
					workshop.setTot_seats(rs.getInt("tot_seats"));
					workshop.setVacant_seats(rs.getInt("vacant_seats"));
					workshop.setFees(rs.getFloat("fees"));
					workshop.setPosted_by(rs.getString("posted_by"));
					workshop.setPosted_on(rs.getString("posted_on"));
					workshop.setStatus(rs.getInt("status"));
				}
				
	             
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw ex;
	        }      
	         
	        return workshop;
	    }
	    
	  
	    public boolean Insert(Workshop workshop)
		{
			String sql = "insert into WORKSHOP(title, conducted_by, w_start_date, w_end_date, duration, tot_seats, vacant_seats, fees, posted_by, posted_on, status, pdf) values" + "(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			Connection con = null;
			con = Connect.getConnection();
			PreparedStatement st;
			
			try
			{
				st = con.prepareStatement(sql);
				
				java.util.Date utilDate = new Date();
				java.sql.Date currentdate = new java.sql.Date(utilDate.getTime());
				
				Part filepart = workshop.getPdf();
				InputStream inputstream =null; 
				inputstream = filepart.getInputStream();
				
				st.setString(1,workshop.getTitle());
				st.setString(2,workshop.getConducted_by());
				st.setString(3,workshop.getW_start_date());
				st.setString(4,workshop.getW_end_date());
				st.setInt(5,workshop.getDuration());
				st.setInt(6,workshop.getTot_seats());
				st.setInt(7,workshop.getTot_seats());
				st.setFloat(8,workshop.getFees());
				st.setString(9,workshop.getPosted_by());
				st.setDate(10,currentdate);
				st.setInt(11,1);
				st.setBlob(12,inputstream);
				
				st.executeUpdate();
				
				return true;
			} catch (Exception e) 
			{
				
				e.printStackTrace();
			}
			return false;
		}

	    
	    
		     
		    public float FeesByID(int id) throws SQLException, ClassNotFoundException {
		      	     
		    	String sql = "SELECT fees FROM WORKSHOP where workshop_id=?";
		    	Connection con = null;
				con = Connect.getConnection();
				PreparedStatement st;
				
		        try {
		        	st = con.prepareStatement(sql);
					st.setInt(1,id);
					ResultSet rs = st.executeQuery();
					
					if(rs.next())
					{
						return(rs.getFloat("fees"));
						
					}
					
		             
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            throw ex;
		        }      
		         
		        return -1;
		    }
		    
		    
		    public boolean validate_seat(int workshop_id) throws SQLException, ClassNotFoundException {
	      	     
		    	String sql = "SELECT vacant_seats FROM WORKSHOP where workshop_id=?";
		    	Connection con = null;
				con = Connect.getConnection();
				PreparedStatement st;
				
		        try {
		        	st = con.prepareStatement(sql);
					st.setInt(1,workshop_id);
					ResultSet rs = st.executeQuery();
					int seats = 0;
					
					if(rs.next())
					{
						seats = rs.getInt("vacant_seats");
					}
					
					if(seats != 0)
						return true;
					else
						return false;
		             
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            throw ex;
		        }      
		    }
		    
		    
		    
}
