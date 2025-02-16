package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.EmailUtility;
import Others.GenerateOTP;
 
/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 *
 * @author www.codejava.net
 *
 */
@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private String host;
    private String port;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
    	
    	HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		int length = 5;
		char[] otp = new char[length];		
		GenerateOTP GenOTP = new GenerateOTP();
		
		otp = GenOTP.sendOTP(length);
		
		session.setAttribute("otp", otp);
		
		
        String recipient = "shubhangik1511@gmail.com";
        String subject = "Connector-OTP";
        String content = "OTP : " + String.valueOf(otp);
 
        String resultMessage = "";
 
        try {
            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                    content);
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/otp.jsp").forward(
                    request, response);
        }
    }
}