package Others;

import java.sql.*;

public class Connect {

	    private static String url = "jdbc:mysql://127.0.0.1:3306/community";    
	    private static String driverName = "com.mysql.jdbc.Driver";   
	    private static String username = "shubhangi";   
	    private static String password = "12345";
	    private static Connection con;


	    public static Connection getConnection() {
	        try {
	            Class.forName(driverName);
	            try {
	                con = DriverManager.getConnection(url, username, password);
	            } catch (SQLException ex) {
	                // log an exception. fro example:
	                System.out.println("Failed to create the database connection."); 
	            }
	        } catch (ClassNotFoundException ex) {
	            // log an exception. for example:
	            System.out.println("Driver not found."); 
	        }
	        return con;
	    }
	}

