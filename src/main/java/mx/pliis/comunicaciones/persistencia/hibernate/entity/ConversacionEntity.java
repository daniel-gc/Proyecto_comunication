package mx.pliis.comunicaciones.persistencia.hibernate.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the conversacion database table.
 * 
 */
@Entity
@Table(name="conversacion")
@NamedQuery(name="ConversacionEntity.findAll", query="SELECT c FROM ConversacionEntity c")
public class ConversacionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_conversacion", unique=true, nullable=false)
	private Long idConversacion;

	@Column(name="fecha_envio", nullable=false)
	private Timestamp fechaEnvio;

	@Column(name="fecha_respuesta")
	private Timestamp fechaRespuesta;

	@Column(name="id_afil_afiliado", nullable=false)
	private Integer idAfilAfiliado;

	@Column(name="id_afil_red_social", nullable=false)
	private Integer idAfilRedSocial;

	@Column(name="id_msg_afiliado", nullable=false, length=1000)
	private String idMsgAfiliado;

	@Column(name="is_cerrada", nullable=false)
	private Boolean isCerrada;

	@Column(name="tiene_adjunto", nullable=false)
	private Boolean tieneAdjunto;

	@Column(name="txt_afiliado", length=1000)
	private String txtAfiliado;

	@Column(name="txt_bot", length=1000)
	private String txtBot;

	//bi-directional many-to-one association to ConversacionEntity
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="padre")
	private ConversacionEntity conversacion;

	//bi-directional many-to-one association to ConversacionEntity
	@OneToMany(mappedBy="conversacion")
	private List<ConversacionEntity> conversacions;

	//bi-directional many-to-one association to EvaluacionEntity
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_evaluacion")
	private EvaluacionEntity evaluacion;

	//bi-directional many-to-one association to PeticionDelegadoEntity
	@OneToMany(mappedBy="conversacion")
	private List<PeticionDelegadoEntity> peticionDelegados;

	//bi-directional many-to-one association to PropiedadConversacionEntity
	@OneToMany(mappedBy="conversacion")
	private List<PropiedadConversacionEntity> propiedadConversacions;

	public ConversacionEntity() {
	}

	public Long getIdConversacion() {
		return this.idConversacion;
	}

	public void setIdConversacion(Long idConversacion) {
		this.idConversacion = idConversacion;
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

	public Integer getIdAfilAfiliado() {
		return this.idAfilAfiliado;
	}

	public void setIdAfilAfiliado(Integer idAfilAfiliado) {
		this.idAfilAfiliado = idAfilAfiliado;
	}

	public Integer getIdAfilRedSocial() {
		return this.idAfilRedSocial;
	}

	public void setIdAfilRedSocial(Integer idAfilRedSocial) {
		this.idAfilRedSocial = idAfilRedSocial;
	}

	public String getIdMsgAfiliado() {
		return this.idMsgAfiliado;
	}

	public void setIdMsgAfiliado(String idMsgAfiliado) {
		this.idMsgAfiliado = idMsgAfiliado;
	}

	public Boolean getIsCerrada() {
		return this.isCerrada;
	}

	public void setIsCerrada(Boolean isCerrada) {
		this.isCerrada = isCerrada;
	}

	public Boolean getTieneAdjunto() {
		return this.tieneAdjunto;
	}

	public void setTieneAdjunto(Boolean tieneAdjunto) {
		this.tieneAdjunto = tieneAdjunto;
	}

	public String getTxtAfiliado() {
		return this.txtAfiliado;
	}

	public void setTxtAfiliado(String txtAfiliado) {
		this.txtAfiliado = txtAfiliado;
	}

	public String getTxtBot() {
		return this.txtBot;
	}

	public void setTxtBot(String txtBot) {
		this.txtBot = txtBot;
	}

	public ConversacionEntity getConversacion() {
		return this.conversacion;
	}

	public void setConversacion(ConversacionEntity conversacion) {
		this.conversacion = conversacion;
	}

	public List<ConversacionEntity> getConversacions() {
		return this.conversacions;
	}

	public void setConversacions(List<ConversacionEntity> conversacions) {
		this.conversacions = conversacions;
	}

	public ConversacionEntity addConversacion(ConversacionEntity conversacion) {
		getConversacions().add(conversacion);
		conversacion.setConversacion(this);

		return conversacion;
	}

	public ConversacionEntity removeConversacion(ConversacionEntity conversacion) {
		getConversacions().remove(conversacion);
		conversacion.setConversacion(null);

		return conversacion;
	}

	public EvaluacionEntity getEvaluacion() {
		return this.evaluacion;
	}

	public void setEvaluacion(EvaluacionEntity evaluacion) {
		this.evaluacion = evaluacion;
	}

	public List<PeticionDelegadoEntity> getPeticionDelegados() {
		return this.peticionDelegados;
	}

	public void setPeticionDelegados(List<PeticionDelegadoEntity> peticionDelegados) {
		this.peticionDelegados = peticionDelegados;
	}

	public PeticionDelegadoEntity addPeticionDelegado(PeticionDelegadoEntity peticionDelegado) {
		getPeticionDelegados().add(peticionDelegado);
		peticionDelegado.setConversacion(this);

		return peticionDelegado;
	}

	public PeticionDelegadoEntity removePeticionDelegado(PeticionDelegadoEntity peticionDelegado) {
		getPeticionDelegados().remove(peticionDelegado);
		peticionDelegado.setConversacion(null);

		return peticionDelegado;
	}

	public List<PropiedadConversacionEntity> getPropiedadConversacions() {
		return this.propiedadConversacions;
	}

	public void setPropiedadConversacions(List<PropiedadConversacionEntity> propiedadConversacions) {
		this.propiedadConversacions = propiedadConversacions;
	}

	public PropiedadConversacionEntity addPropiedadConversacion(PropiedadConversacionEntity propiedadConversacion) {
		getPropiedadConversacions().add(propiedadConversacion);
		propiedadConversacion.setConversacion(this);

		return propiedadConversacion;
	}

	public PropiedadConversacionEntity removePropiedadConversacion(PropiedadConversacionEntity propiedadConversacion) {
		getPropiedadConversacions().remove(propiedadConversacion);
		propiedadConversacion.setConversacion(null);

		return propiedadConversacion;
	}

}