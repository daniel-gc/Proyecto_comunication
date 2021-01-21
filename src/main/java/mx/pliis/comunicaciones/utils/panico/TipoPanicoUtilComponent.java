package mx.pliis.comunicaciones.utils.panico;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import mx.pliis.comunicaciones.dto.TipoPanicoDTO;
import mx.pliis.comunicaciones.persistencia.hibernate.entity.TipoPanicoEntity;

@Component
public class TipoPanicoUtilComponent {
	public TipoPanicoDTO copyFromEntityToDTO(TipoPanicoEntity ent) {
		TipoPanicoDTO dto = new TipoPanicoDTO();

		try {
			BeanUtils.copyProperties(dto, ent);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	public List<TipoPanicoDTO> copyFromEntityListToDTOList(List<TipoPanicoEntity> listaEnt) {
		List<TipoPanicoDTO> listaDto = new ArrayList<>();

		listaEnt.forEach(elem -> {
			TipoPanicoDTO dto = copyFromEntityToDTO(elem);
			listaDto.add(dto);
		});

		return listaDto;
	}

}
