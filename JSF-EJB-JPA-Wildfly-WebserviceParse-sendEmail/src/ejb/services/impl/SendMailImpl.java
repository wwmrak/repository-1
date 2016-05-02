package ejb.services.impl;

import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class SendMailImpl {
	
	public void sendMail(List<String> listPodaciOsobe) {
		
	System.out.println(listPodaciOsobe);
	
    final String username = "mmmrak821@gmail.com";
    final String password = "mmrak821111";

    String emailToAddress = listPodaciOsobe.get(2);
    
    Properties props = new Properties();
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
      });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("mmmrak821@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(emailToAddress));
        message.setSubject("E-mail poruka");
        
        if (listPodaciOsobe.size() > 3) {
        message.setText("Mail poslan. ime: " + listPodaciOsobe.get(15) + ", prezime: " 
        		+ listPodaciOsobe.get(16) + ", e-mail: " + listPodaciOsobe.get(17) + ", adresa: "
        		+ listPodaciOsobe.get(4) + " " + listPodaciOsobe.get(5) + " " + listPodaciOsobe.get(6));
        } else message.setText("Mail poslan. ime: " + listPodaciOsobe.get(0) + ", prezime: " 
        		+ listPodaciOsobe.get(1) + ", e-mail: " + listPodaciOsobe.get(2));

        Transport.send(message);
        
        System.out.println("\nMail poslan.\n");
        
    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}
}
