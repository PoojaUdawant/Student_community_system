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
import DAO.LoginDAO;
import Others.GenerateOTP;

/**
 * Servlet implementation class forgot_password
 */
@WebServlet("/forgot_password")
public class forgot_password extends HttpServlet {
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
		
		long mob_no = Long.parseLong(request.getParameter("mob_no"));
		String email = request.getParameter("email");
		String password = null;
		
		LoginDAO logindao = new LoginDAO();
		password = logindao.resetPassword(mob_no, email);
		
		if(password != null)
		{
	        String recipient = "shubhangik1511@gmail.com";
	        String subject = "Forgot Password?";
	        String content = "Your Password : " + password;
	 
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
	            session.setAttribute("msg","Please check your Email for password");
	            getServletContext().getRequestDispatcher("/index.jsp").forward(
	                    request, response);
	        }
		}
		else
		{
			session.setAttribute("msg1","Sorry! Please try again!!!");
			response.sendRedirect("ForgotPassword.jsp");
		}
	}
		

}
