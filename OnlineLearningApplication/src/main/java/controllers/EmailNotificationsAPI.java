package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EmailNotificationsAPI")
public class EmailNotificationsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public EmailNotificationsAPI() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String[] learnerEmails = request.getParameterValues("learnerEmails");
		String emailSubject = request.getParameter("emailSubject");
		String emailText = request.getParameter("emailText");
		try(PrintWriter out = response.getWriter()){
			if (emailSubject ==null || emailSubject.trim().isEmpty()) {  //validate that the email subject is not null or empty
			    out.write("Please provide valid email subject");
			    return;
			}
			if (emailText ==null || emailText.trim().isEmpty()) {   //validate that the email text is not null or empty
			    out.write("Please provide valid email text");
			    return;
			}
			if(learnerEmails != null && learnerEmails.length>0){     //if the administrator has selected learners, notify them otherwise return an error message
				try{
					notifyLearner(learnerEmails, emailSubject, emailText);  
					out.write("Emails delivered successfully");
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			else{
				out.write("Please select learners to notify them by email");
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void notifyLearner (String[] learnerEmails, String emailSubject, String emailText) throws MessagingException 
	{
		String host="smtp.gmail.com";
		final String adminEmailAddress = "onlineLearningapp.admn67@gmail.com";    // the sender email address
        final String adminPassword = "euxx ytis emnd rjzh";                       //the sender application-specific password
        
        Properties property = new Properties();
        property.put("mail.smtp.host", host);    //Configure an SMTP server using the TLS protocol on port 587
        property.put("mail.smtp.port", "587");
        property.put("mail.smtp.auth", "true");
        property.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(property, new javax.mail.Authenticator() {  //create a new session with the server properties and an authenticator
            protected PasswordAuthentication getPasswordAuthentication()  {
                return new PasswordAuthentication(adminEmailAddress, adminPassword);
            }
        });
        
        for (String learnerEmail : learnerEmails) {
            MimeMessage email = new MimeMessage(session);          //create an email object
            email.setFrom(new InternetAddress(adminEmailAddress));   //specify the sender, which is the administrator in this case
            email.addRecipient(Message.RecipientType.TO, new InternetAddress(learnerEmail));   //specify the recipient
            email.setSubject(emailSubject);           //specify the email subject
            email.setText(emailText);              //specify the email text

            Transport.send(email);          //forward the email to the recipient
        }
	}
	
	
}


