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
		
		try(PrintWriter out = response.getWriter())
		{
			if (emailSubject ==null || emailSubject.trim().isEmpty()) 
			{
			    out.write("Error");
			    return;
			}
			if (emailText ==null || emailText.trim().isEmpty()) 
			{
			    out.write("Error");
			    return;
			}
			
			if(learnerEmails != null && learnerEmails.length>0)
			{
				try
				{
					notifyLearner(learnerEmails, emailSubject, emailText);
					out.write("Success");
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			else
			{
				out.write("Error");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void notifyLearner (String[] learnerEmails, String emailSubject, String emailText) throws MessagingException 
	{
		String host="smtp.gmail.com";
		final String adminEmailAddress = "onlineLearningapp.admn67@gmail.com";
        final String adminPassword = "euxx ytis emnd rjzh";
        
        Properties property = new Properties();
        property.put("mail.smtp.host", host);
        property.put("mail.smtp.port", "587");
        property.put("mail.smtp.auth", "true");
        property.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(property, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(adminEmailAddress, adminPassword);
            }
        });
        
        for (String learnerEmail : learnerEmails) 
        {
            MimeMessage email = new MimeMessage(session);
            email.setFrom(new InternetAddress(adminEmailAddress));
            email.addRecipient(Message.RecipientType.TO, new InternetAddress(learnerEmail));
            email.setSubject(emailSubject);
            email.setText(emailText);

            Transport.send(email);
        }


	}
	
	
	
	
	
	

}
