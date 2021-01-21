package mx.pliis.comunicaciones.persistencia.hibernate.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the peticion_delegado database table.
 * 
 */
@Entity
@Table(name="peticion_delegado")
@NamedQuery(name="PeticionDelegadoEntity.findAll", query="SELECT p FROM PeticionDelegadoEntity p")
public class PeticionDelegadoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_peticion_delegado", unique=true, nullable=false)
	private Integer idPeticionDelegado;

	@Column(name="fecha_envio", nullable=false)
	private Timestamp fechaEnvio;

	@Column(name="fecha_respuesta")
	private Timestamp fechaRespuesta;

	@Column(name="id_afil_delegado", nullable=false)
	private Integer idAfilDelegado;

	@Column(length=1000)
	private String respuesta;

	//bi-directional many-to-one association to ConversacionEntity
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_conversacion", nullable=false)
	private ConversacionEntity conversacion;

	public PeticionDelegadoEntity() {
	}

	public Integer getIdPeticionDelegado() {
		return this.idPeticionDelegado;
	}

	public void setIdPeticionDelegado(Integer idPeticionDelegado) {
		this.idPeticionDelegado = idPeticionDelegado;
	}

	public Timestamp getFechaEnvio() {
		return this.fechaEnvio;
	}

	public void setFechaEnvio(Timestamp fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Timestamp getFechaRespuesta() {
		return this.fechaRespuesta;
	}

	public void setFechaRespuesta(Timestamp fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	public Integer getIdAfilDelegado() {
		return this.idAfilDelegado;
	}

	public void setIdAfilDelegado(Integer idAfilDelegado) {
		this.idAfilDelegado = idAfilDelegado;
	}

	public String getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public ConversacionEntity getConversacion() {
		return this.conversacion;
	}

	public void setConversacion(ConversacionEntity conversacion) {
		this.conversacion = conversacion;
	}

}