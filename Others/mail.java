package Others;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail {
private String email;
private String msg;
private String sub;

public  mail(String email, String msg, String sub) {
super();
this.email = email;
this.msg = msg;
this.sub = sub;
}

public static boolean send(String email,String msg,String sub)
{
System.out.println("Message sent");
boolean isSent=false;
isSent=new mail(email,msg,sub).sendmail();
return isSent;
}

private boolean sendmail()
{
System.out.println("hi");
boolean isdone=false;
Properties props= new Properties();
props.put("mail.smtp.auth", "true");
  props.put("mail.smtp.port", "587");
  props.put("mail.smtp.starttls.enable", "true");
  props.put("mail.smtp.socketFactory.port", "587");
  props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
  props.put("mail.smtp.socketFactory.fallback", "false");
  props.setProperty("mail.smtp.quitwait", "false");
Session session=Session.getInstance(props, new javax.mail.Authenticator() {
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("connectorconnectorconab@gmail.com","123456AB");
	}
});
try {
	Message message=new MimeMessage(session);
	message.setFrom(new InternetAddress("connectorconnectorconab@gmail.com"));
	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
	message.setSubject(sub);
	message.setText(msg);
	Transport.send(message);
	isdone=true;	
} catch(MessagingException e) {
	throw new RuntimeException(e); 
}
return isdone;
}


}