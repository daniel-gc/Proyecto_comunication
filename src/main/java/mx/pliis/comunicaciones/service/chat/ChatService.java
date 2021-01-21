package mx.pliis.comunicaciones.service.chat;

import mx.pliis.comunicaciones.dto.ChatEmailDTO;

public interface ChatService {

	boolean sendEmail(ChatEmailDTO chatEmailDTO);
}
