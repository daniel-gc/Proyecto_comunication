package mx.pliis.comunicaciones.persistencia.hibernate.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the evaluacion database table.
 * 
 */
@Entity
@Table(name="evaluacion")
@NamedQuery(name="EvaluacionEntity.findAll", query="SELECT e FROM EvaluacionEntity e")
public class EvaluacionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_evaluacion", unique=true, nullable=false)
	private Integer idEvaluacion;

	@Column(nullable=false, length=50)
	private String nombre;

	//bi-directional many-to-one association to ConversacionEntity
	@OneToMany(mappedBy="evaluacion")
	private List<ConversacionEntity> conversacions;

	public EvaluacionEntity() {
	}

	public Integer getIdEvaluacion() {
		return this.idEvaluacion;
	}

	public void setIdEvaluacion(Integer idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ConversacionEntity> getConversacions() {
		return this.conversacions;
	}

	public void setConversacions(List<ConversacionEntity> conversacions) {
		this.conversacions = conversacions;
	}

	public ConversacionEntity addConversacion(ConversacionEntity conversacion) {
		getConversacions().add(conversacion);
		conversacion.setEvaluacion(this);

		return conversacion;
	}

	public ConversacionEntity removeConversacion(ConversacionEntity conversacion) {
		getConversacions().remove(conversacion);
		conversacion.setEvaluacion(null);

		return conversacion;
	}

}