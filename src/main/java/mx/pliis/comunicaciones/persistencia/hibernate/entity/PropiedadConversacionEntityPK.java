package mx.pliis.comunicaciones.persistencia.hibernate.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the propiedad_conversacion database table.
 * 
 */
@Embeddable
public class PropiedadConversacionEntityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_propiedad", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idPropiedad;

	@Column(name="id_conversacion", insertable=false, updatable=false, unique=true, nullable=false)
	private Long idConversacion;

	public PropiedadConversacionEntityPK() {
	}
	public Integer getIdPropiedad() {
		return this.idPropiedad;
	}
	public void setIdPropiedad(Integer idPropiedad) {
		this.idPropiedad = idPropiedad;
	}
	public Long getIdConversacion() {
		return this.idConversacion;
	}
	public void setIdConversacion(Long idConversacion) {
		this.idConversacion = idConversacion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PropiedadConversacionEntityPK)) {
			return false;
		}
		PropiedadConversacionEntityPK castOther = (PropiedadConversacionEntityPK)other;
		return 
			this.idPropiedad.equals(castOther.idPropiedad)
			&& this.idConversacion.equals(castOther.idConversacion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPropiedad.hashCode();
		hash = hash * prime + this.idConversacion.hashCode();
		
		return hash;
	}
}