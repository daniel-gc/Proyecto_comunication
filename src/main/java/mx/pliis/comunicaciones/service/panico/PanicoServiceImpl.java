package mx.pliis.comunicaciones.service.panico;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import mx.pliis.comunicaciones.dto.PanicoDTO;
import mx.pliis.comunicaciones.dto.TipoPanicoDTO;
import mx.pliis.comunicaciones.persistencia.hibernate.entity.PanicoEntity;
import mx.pliis.comunicaciones.persistencia.hibernate.entity.TipoPanicoEntity;
import mx.pliis.comunicaciones.persistencia.hibernate.repository.PanicoEntityRepository;
import mx.pliis.comunicaciones.persistencia.hibernate.repository.TipoPanicoEntityRepository;
import mx.pliis.comunicaciones.utils.panico.TipoPanicoUtilComponent;

@Service
@Log4j2
public class PanicoServiceImpl implements PanicoService {
	@Autowired
	private TipoPanicoEntityRepository tipoPanicoEntityRepository;
	@Autowired
	private TipoPanicoUtilComponent tipoPanicoUtilComponent;
	@Autowired
	private PanicoEntityRepository panicoEntityRepository;
	@Value("${username.email.addr}")
	private String username;
	@Value("${username.email.passw}")
	private String password;

	@Override
	@Transactional(readOnly = true)
	public List<TipoPanicoDTO> getAllTipoPanico() {
		List<TipoPanicoEntity> listaEnt = tipoPanicoEntityRepository.findAll();
		return !listaEnt.isEmpty() ? tipoPanicoUtilComponent.copyFromEntityListToDTOList(listaEnt)
				: new ArrayList<>();

	}

	@Override
	@Transactional(readOnly = false)
	public Boolean crearPanico(PanicoDTO panicoDTO) {
		List<TipoPanicoEntity> listaTipoPanico = new ArrayList<>();
		panicoDTO.getTipoPanicos().forEach(val -> {
			TipoPanicoEntity tipoPanicoEntity = tipoPanicoEntityRepository.findByCodigo(val);
			listaTipoPanico.add(tipoPanicoEntity);
		});
		PanicoEntity panicoEntity = new PanicoEntity();
		Instant instant = Instant.now();
		Timestamp timestamp = Timestamp.from(instant);

		panicoEntity.setAfIdAfiliado(panicoDTO.getAfIdAfiliado());
		panicoEntity.setDescripcion(panicoDTO.getDescripcion());
		panicoEntity.setEsIdCentroTrabajo(panicoDTO.getEsIdCentroTrabajo());
		panicoEntity.setFecha(timestamp);
		panicoEntity.setNombreDenunciado(panicoDTO.getNombreDenunciado());
		panicoEntity.setTipoPanicos(listaTipoPanico);
		try {
			panicoEntityRepository.save(panicoEntity);
		} catch (Exception e) {
			return false;
		}
		this.sendEmail(panicoDTO, listaTipoPanico);

		return true;
	}

	private void sendEmail(PanicoDTO panicoDTO, List<TipoPanicoEntity> listaTipoPanico) {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.ssl.checkserveridentity", true);
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		StringBuilder direccionesBuilder = new StringBuilder();
		StringBuilder nombresPanicosBuilder = new StringBuilder();
		
		for (int i = 0; i < panicoDTO.getCorreos().size(); i++) {
			direccionesBuilder.append(panicoDTO.getCorreos().get(i));
			if (i != panicoDTO.getCorreos().size() - 1) {
				direccionesBuilder.append(", ");
			}
		}
		
		for(int i = 0; i < listaTipoPanico.size(); i++) {
			nombresPanicosBuilder.append(listaTipoPanico.get(i).getCodigo());
			if(i != listaTipoPanico.size() - 1){
				nombresPanicosBuilder.append(", ");
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
			contenidoBuilder.append("Descripción: ").append(panicoDTO.getDescripcion()).append("\n\n");

			if (panicoDTO.getNombreDenunciado() != null)
				contenidoBuilder.append("Acusado: ").append(panicoDTO.getNombreDenunciado()).append("\n\n");

			contenidoBuilder.append("Empresa: ").append(panicoDTO.getNombreEmpresa()).append("\n\n")
					.append("Centro de trabajo: ").append(panicoDTO.getNombreCentroTrabajo());

			String subject = "Pánico: " + nombresPanicosBuilder.toString();

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("pliiscorporate@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(direccionesBuilder.toString()));
			message.setSubject(subject);
			message.setText(contenidoBuilder.toString());

			Transport.send(message);

			log.info("Email enviado");

		} catch (MessagingException e) {
			log.error("Excepción: %s", e.getLocalizedMessage());
		}

	}

}
