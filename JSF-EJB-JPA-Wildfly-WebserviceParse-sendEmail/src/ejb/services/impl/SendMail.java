package ejb.services.impl;

import java.util.Map;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class SendMail {
	final static String username = "mmmrak821@gmail.com";
    final static String password = "mmrak821111";
	
	public void sendMail(Map<String, String> personalInfoFromForm) {
		String receivingEmailAddress = personalInfoFromForm.get("email");
    
		Properties props = fillPropertiesObject();

		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

		try {
			Message message = createMessageToSend(personalInfoFromForm, receivingEmailAddress, session);
			Transport.send(message);
    	} catch (MessagingException e) {
    		throw new RuntimeException(e);
    	}
	}

	private Message createMessageToSend(Map<String, String> personalInfoFromForm, String emailToAddress,
			Session session) throws MessagingException, AddressException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("mmmrak821@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailToAddress));
		message.setSubject("E-mail message");
      
		if (personalInfoFromForm.size() > 3) {
			message.setText("E-mail sent. Name : " + personalInfoFromForm.get("name2") + ", surname: " 
			+ personalInfoFromForm.get("surname2") + ", e-mail: " + personalInfoFromForm.get("email2") + ", address: "
			+ personalInfoFromForm.get("street") + " " + personalInfoFromForm.get("suite") + " " + personalInfoFromForm.get("city"));
		} else message.setText("E-Mail sent. Name: " + personalInfoFromForm.get("name") + ", surname: " 
			+ personalInfoFromForm.get("surname") + ", e-mail: " + personalInfoFromForm.get("email"));
		
		return message;
	}

	private Properties fillPropertiesObject() {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		return props;
	}
}
