package mx.pliis.comunicaciones.api_rest_controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.pliis.comunicaciones.dto.ChatEmailDTO;
import mx.pliis.comunicaciones.service.chat.ChatService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chat")
public class ChatRestController {
	@Autowired
	private ChatService chatService;

	@PostMapping(value = "/email", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> enviarEmail(@RequestBody @Valid ChatEmailDTO chatEmailDTO) {
		HttpHeaders responseHeaders = new HttpHeaders();
		Boolean retorno = false;

		HttpStatus status = HttpStatus.OK;

		try {
			retorno = chatService.sendEmail(chatEmailDTO);
			if (!retorno)
				status = HttpStatus.NO_CONTENT;
		} catch (Exception e) {
			System.err.print(e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			responseHeaders.set("Mensaje", "Ocurrió un error: " + e.getLocalizedMessage());
			return new ResponseEntity<Boolean>(retorno, responseHeaders, status);
		}

		return new ResponseEntity<Boolean>(retorno, responseHeaders, status);
	}
}
