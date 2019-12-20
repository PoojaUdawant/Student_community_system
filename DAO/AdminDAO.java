	package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Admin_login_model;
import model.Workshop;
import model.admin_model;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AdminDAO {
	private static final String String = null;
	public static int check(Admin_login_model ad) throws ClassNotFoundException, SQLException
	{
		int status=0;
		DBconnectivity db = new DBconnectivity();
		String q = "SELECT *FROM ADMIN_LOGIN WHERE login_id='"+ad.getLogin_id()+"' AND login_password='"+ad.getLogin_password()+"' ";
		ResultSet rs =db.retrieveValue(q);
		while(rs.next())
		{	
			status=1;
		}
		return status;
	}
	public static boolean insert(admin_model md) throws ClassNotFoundException, SQLException
	{
		DBconnectivity db =new DBconnectivity();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();
		  int x= db.insertValue("INSERT INTO ADMIN_INFO VALUES('"+md.getUsername()+"','"+md.getFirstname()+"','"+md.getLastname()+"','"+md.getMob_no()+"','"+md.getEmail()+"','"+dtf.format(now)+"','"+0+"')");
		  int y = db.insertValue("INSERT INTO ADMIN_LOGIN(login_id) VALUES('"+md.getUsername()+"')");
		   
		  if(x==1 && y==1)return true;
		   return false;

	}
	public static List<admin_model> listAllAdmin() throws ClassNotFoundException, SQLException{
		List<admin_model> listAdmin = new ArrayList<>();
		DBconnectivity db = new DBconnectivity();
		ResultSet rs = db.retrieveValue("SELECT *FROM ADMIN_INFO");
		while(rs.next())
		{
			String username = rs.getString(1);
			String firstname = rs.getString(2);
			String lastname = rs.getString(3);
			String mob_no = rs.getString(4);
			String email = rs.getString(5);
			Date date_joined = rs.getDate(6);
			admin_model admin = new admin_model(username,firstname,lastname,mob_no,email,date_joined);
			listAdmin.add(admin);
		}
		return listAdmin;
		
	}
	public static int deleteAdmin(String username) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		DBconnectivity db = new DBconnectivity();
	
		int rs1 = db.insertValue("DELETE FROM ADMIN_LOGIN WHERE login_id='"+username+"'");
		int rs = db.insertValue("DELETE FROM ADMIN_INFO WHERE username='"+username+"'");
		return rs&rs1;
	}
	
	public static void changerole(String username) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		DBconnectivity db = new DBconnectivity();
		int role =2;
		ResultSet rs = db.retrieveValue("SELECT role_id FROM HAS WHERE username='"+username+"'");
		if(rs.next()) {
			role=rs.getInt(1);
			System.out.println(rs.getInt(1));
		}
		if(role == 2)
			db.insertValue("UPDATE HAS SET Role_id='"+1+"' WHERE username='"+username+"'");
		
		else
			db.insertValue("UPDATE HAS SET Role_id='"+2+"' WHERE username='"+username+"'");

	}
	public static void sendmail(String from,String to) throws ClassNotFoundException, SQLException {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		//props.put("mail.smtp.ssl", value);
		//props.put("mail.smtp.sender.address", value);
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("shreyk1999@gmail.com","$"); //jisse bhejena hai
				}
			});

		try {

			Message message = new MimeMessage(session);
	                    message.setFrom(new InternetAddress("shreyk1999@gmail.com")); //jisse bhejna hai
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to)); // Jisko bhejna hai 
			message.setSubject("Congratulations");
			message.setText("You have been appointed as an Admin of CONNECTOR web Project.You have a golden chance to work with Shrey.Your default passwaord=password");
	           

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	/*public static String getuserEmail(String id) throws ClassNotFoundException, SQLException {
		DBconnectivity db =new DBconnectivity();
		ResultSet rs = db.retrieveValue("select email from ADMIN_INFO where username like '"+id+"'");
		System.out.println(rs);
		String from =null;
		return from;
		// TODO Auto-generated method stub
		
	}*/
	public static int getrole(String username) throws ClassNotFoundException, SQLException {
		int role = 0;
		DBconnectivity db = new DBconnectivity();
		
		ResultSet rs = db.retrieveValue("SELECT role_id from HAS where username='"+username+"'");
		if(rs.next()) {
			role=rs.getInt(1);
			System.out.println(rs.getInt(1));
		}
		return role;
		// TODO Auto-generated method stub
		
	}
	public static List<admin_model> search(String from, String to) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<admin_model> listAdmin = new ArrayList<>();
		DBconnectivity db = new DBconnectivity();
		ResultSet rs = db.retrieveValue("SELECT *FROM ADMIN_INFO");
		while(rs.next())
		{
			String username = rs.getString(1);
			String firstname = rs.getString(2);
			String lastname = rs.getString(3);
			String mob_no = rs.getString(4);
			String email = rs.getString(5);
			Date date_joined = rs.getDate(6);
			admin_model admin = new admin_model(username,firstname,lastname,mob_no,email,date_joined);
			System.out.println(username);
			listAdmin.add(admin);
		}
		return listAdmin;
	}
	public static List<Workshop> searchWorkshop(String from, String to) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Workshop> listWorkshop = new ArrayList<>();
		DBconnectivity db = new DBconnectivity();
		ResultSet rs = db.retrieveValue("SELECT workshop_id,title,conducted_by,w_start_date FROM WORKSHOP WHERE w_start_date BETWEEN '"+from+"' AND '"+to+"' ");
		while(rs.next())
		{
			int id =rs.getInt(1);
			String title = rs.getString(1);
			String conducted_by = rs.getString(2);
			String w_start_date = rs.getString(3);
			
			
			System.out.println(title);
			System.out.println(conducted_by);
			System.out.println(w_start_date);
			Workshop workshop = new Workshop(id,title,conducted_by,w_start_date);
			listWorkshop.add(workshop);
		}
		return listWorkshop;
	}
	
}
