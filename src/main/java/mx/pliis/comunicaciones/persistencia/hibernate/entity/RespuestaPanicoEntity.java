package mx.pliis.comunicaciones.persistencia.hibernate.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the respuesta_panico database table.
 * 
 */
@Entity
@Table(name="respuesta_panico")
@NamedQuery(name="RespuestaPanicoEntity.findAll", query="SELECT r FROM RespuestaPanicoEntity r")
public class RespuestaPanicoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_respuesta_panico", unique=true, nullable=false)
	private Integer idRespuestaPanico;

	@Column(nullable=false, length=1000)
	private String contenido;

	@Column(nullable=false)
	private Timestamp fecha;

	//bi-directional many-to-one association to PanicoEntity
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_panico", nullable=false)
	private PanicoEntity panico;

	public RespuestaPanicoEntity() {
	}

	public Integer getIdRespuestaPanico() {
		return this.idRespuestaPanico;
	}

	public void setIdRespuestaPanico(Integer idRespuestaPanico) {
		this.idRespuestaPanico = idRespuestaPanico;
	}

	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public PanicoEntity getPanico() {
		return this.panico;
	}

	public void setPanico(PanicoEntity panico) {
		this.panico = panico;
	}

}