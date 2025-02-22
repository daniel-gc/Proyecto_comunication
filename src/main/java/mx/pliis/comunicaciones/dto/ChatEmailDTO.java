package mx.pliis.comunicaciones.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ChatEmailDTO {
	@NotEmpty(message = "Debe existir el cuerpo de la conversación")
	private String conversacion;
	@NotNull(message = "El centro de trabajo es obligatorio")
	private Integer esIdCentroTrabajo;
	@NotEmpty(message = "Debe existir al menos una dirección de email")
	private List<@NotEmpty String> correos;
	@NotEmpty(message = "La dirección de email del afiliado es obligatoria")
	@Email
	private String emailAfiliado;
}
