package mx.pliis.comunicaciones.service.chat;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import mx.pliis.comunicaciones.dto.ChatEmailDTO;

@Service
@Log4j2
public class ChatServiceImpl implements ChatService {
	@Value("${username.email.addr}")
	private String username;
	@Value("${username.email.passw}")
	private String password;


	@Override
	public boolean sendEmail(ChatEmailDTO chatEmailDTO) {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.ssl.checkserveridentity", true);
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		StringBuilder direccionesBuilder = new StringBuilder();

		for (int i = 0; i < chatEmailDTO.getCorreos().size(); i++) {
			direccionesBuilder.append(chatEmailDTO.getCorreos().get(i));
			if (i != chatEmailDTO.getCorreos().size() - 1) {
				direccionesBuilder.append(", ");
			}
		}


		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			StringBuilder contenidoBuilder = new StringBuilder();
			contenidoBuilder.append("Pregunta realizada por: ").append(chatEmailDTO.getEmailAfiliado()).append("\n\n");


			contenidoBuilder.append("Contenido: ").append(chatEmailDTO.getConversacion()).append("\n\n");

			String subject = "Mensaje enviado desde el chat bot. Pregunta sin respuesta.";

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("pliiscorporate@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(direccionesBuilder.toString()));
			message.setSubject(subject);
			message.setText(contenidoBuilder.toString());

			Transport.send(message);

			log.info("Email enviado.");

		} catch (MessagingException e) {
			log.error("Excepción: %s", e.getLocalizedMessage());
			return false;
		}
		return true;
	}

}
