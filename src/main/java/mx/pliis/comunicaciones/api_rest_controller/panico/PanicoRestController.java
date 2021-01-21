package mx.pliis.comunicaciones.api_rest_controller.panico;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.pliis.comunicaciones.dto.PanicoDTO;
import mx.pliis.comunicaciones.dto.TipoPanicoDTO;
import mx.pliis.comunicaciones.service.panico.PanicoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/panico")
//@Validated
public class PanicoRestController {
	@Autowired
	private PanicoService panicoService;

	@GetMapping("/todosTipos")
	public ResponseEntity<List<TipoPanicoDTO>> getAllPanico() {
		HttpHeaders responseHeaders = new HttpHeaders();
		List<TipoPanicoDTO> listaTipoPanicoDTO = new ArrayList<>();
		HttpStatus status = HttpStatus.OK;

		try {
			listaTipoPanicoDTO = panicoService.getAllTipoPanico();
			if (listaTipoPanicoDTO.size() == 0)
				status = HttpStatus.NO_CONTENT;
		} catch (Exception e) {
			System.err.print(e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			responseHeaders.set("Mensaje", "Ocurrió un error: " + e.getLocalizedMessage());
			return new ResponseEntity<List<TipoPanicoDTO>>(listaTipoPanicoDTO, responseHeaders, status);
		}

		return new ResponseEntity<List<TipoPanicoDTO>>(listaTipoPanicoDTO, responseHeaders, status);
	}

	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> crearPanico(@RequestBody @Valid PanicoDTO panicoDTO,
			BindingResult bindingResult) {
		HttpHeaders responseHeaders = new HttpHeaders();
		Boolean retorno = false;

		if (bindingResult.hasErrors()) {
			String mensaje = bindingResult.getAllErrors().toString();
			responseHeaders.add("Mensaje", "Ocurrió un error: " + mensaje);
			return new ResponseEntity<Boolean>(false, responseHeaders, HttpStatus.CONFLICT);
		}

		HttpStatus status = HttpStatus.OK;

		try {
			retorno = panicoService.crearPanico(panicoDTO);
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
