package mx.pliis.comunicaciones.persistencia.hibernate.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the propiedad_conversacion database table.
 * 
 */
@Entity
@Table(name="propiedad_conversacion")
@NamedQuery(name="PropiedadConversacionEntity.findAll", query="SELECT p FROM PropiedadConversacionEntity p")
public class PropiedadConversacionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PropiedadConversacionEntityPK id;

	@Column(nullable=false, length=500)
	private String valor;

	//bi-directional many-to-one association to ConversacionEntity
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_conversacion", nullable=false, insertable=false, updatable=false)
	private ConversacionEntity conversacion;

	//bi-directional many-to-one association to PropiedadEntity
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_propiedad", nullable=false, insertable=false, updatable=false)
	private PropiedadEntity propiedad;

	public PropiedadConversacionEntity() {
	}

	public PropiedadConversacionEntityPK getId() {
		return this.id;
	}

	public void setId(PropiedadConversacionEntityPK id) {
		this.id = id;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public ConversacionEntity getConversacion() {
		return this.conversacion;
	}

	public void setConversacion(ConversacionEntity conversacion) {
		this.conversacion = conversacion;
	}

	public PropiedadEntity getPropiedad() {
		return this.propiedad;
	}

	public void setPropiedad(PropiedadEntity propiedad) {
		this.propiedad = propiedad;
	}

}