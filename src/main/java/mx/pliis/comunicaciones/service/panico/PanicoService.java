package mx.pliis.comunicaciones.service.panico;

import java.util.List;

import mx.pliis.comunicaciones.dto.PanicoDTO;
import mx.pliis.comunicaciones.dto.TipoPanicoDTO;

public interface PanicoService {
	public List<TipoPanicoDTO> getAllTipoPanico();

	public Boolean crearPanico(PanicoDTO panicoDTO);

}
