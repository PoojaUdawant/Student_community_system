package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBconnectivity {
	Connection con;
	ResultSet rs;
	
	private void connect() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/community", "shubhangi", "12345");
		
	}
	
	public ResultSet retrieveValue(String query) throws ClassNotFoundException, SQLException{
		connect();
		Statement stmt = con.createStatement();
		rs= stmt.executeQuery(query);
		System.out.println("Here is the query: "+ query);
		return rs;
	}
	
	public int insertValue(String query) throws ClassNotFoundException, SQLException{
		connect();
		Statement stmt = con.createStatement();
		int result = stmt.executeUpdate(query);
		System.out.println("Here is the query: "+ query);
		con.close();
		return result;
	}
	
}
