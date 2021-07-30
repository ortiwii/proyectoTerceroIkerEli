package model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

	  	private final Properties props = new Properties();

//	    private String password;
	 
//	    private Session session;	
	 
//	    private void init() {
//	 
//
//	    }
	 
	    public void sendEmail(){
	 
	        // Nombre del host de correo, es smtp.gmail.com
	        props.setProperty("mail.smtp.host", "smtp.gmail.com");

	        // TLS si está disponible
	        props.setProperty("mail.smtp.starttls.enable", "true");

	        // Puerto de gmail para envio de correos
	        props.setProperty("mail.smtp.port","587");

	        // Nombre del usuario
	        props.setProperty("mail.smtp.user", "ergorka@gmail.com");

	        // Si requiere o no usuario y password para conectarse.
	        props.setProperty("mail.smtp.auth", "true");

	        Session session = Session.getDefaultInstance(props);

	        // Para obtener un log de salida más extenso
	        session.setDebug(true);	       	        
	        MimeMessage message = new MimeMessage(session);

	        // Quien envia el correo
	        try {
	            message.setFrom(new InternetAddress("ergorka@gmail.com"));

	            // A quien va dirigido
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress("ortiwii@gmail.com"));

	            message.setSubject("Asunto");

	            message.setText("Hola caracola");

	            Transport t = session.getTransport("smtp");

	            // Aqui usuario y password de gmail
	            t.connect("ergorka@gmail.com","ForReallyBigMistakes@0718-)");
	            t.sendMessage(message,message.getAllRecipients());
	            t.close();
	            
	        } catch (MessagingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

}
