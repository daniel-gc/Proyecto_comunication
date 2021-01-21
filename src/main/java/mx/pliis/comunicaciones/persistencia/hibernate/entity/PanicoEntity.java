package mx.pliis.comunicaciones.persistencia.hibernate.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the panico database table.
 * 
 */
@Entity
@Table(name="panico")
@NamedQuery(name="PanicoEntity.findAll", query="SELECT p FROM PanicoEntity p")
public class PanicoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_panico", unique=true, nullable=false)
	private Long idPanico;

	@Column(name="af_id_afiliado")
	private Integer afIdAfiliado;

	@Column(length=2147483647)
	private String descripcion;

	@Column(name="es_id_centro_trabajo", nullable=false)
	private Integer esIdCentroTrabajo;

	@Column(nullable=false)
	private Timestamp fecha;

	@Column(name="nombre_denunciado", length=500)
	private String nombreDenunciado;

	//bi-directional many-to-one association to RespuestaPanicoEntity
	@OneToMany(mappedBy="panico")
	private List<RespuestaPanicoEntity> respuestaPanicos;

	//bi-directional many-to-many association to TipoPanicoEntity
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "panico_especifico", joinColumns = {
			@JoinColumn(name = "id_panico", referencedColumnName = "id_panico") }, inverseJoinColumns = {
					@JoinColumn(name = "id_tipo_panico", referencedColumnName = "id_tipo_panico") })
	private Collection<TipoPanicoEntity> tipoPanicos;

	public PanicoEntity() {
	}

	public Long getIdPanico() {
		return this.idPanico;
	}

	public void setIdPanico(Long idPanico) {
		this.idPanico = idPanico;
	}

	public Integer getAfIdAfiliado() {
		return this.afIdAfiliado;
	}

	public void setAfIdAfiliado(Integer afIdAfiliado) {
		this.afIdAfiliado = afIdAfiliado;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEsIdCentroTrabajo() {
		return this.esIdCentroTrabajo;
	}

	public void setEsIdCentroTrabajo(Integer esIdCentroTrabajo) {
		this.esIdCentroTrabajo = esIdCentroTrabajo;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getNombreDenunciado() {
		return this.nombreDenunciado;
	}

	public void setNombreDenunciado(String nombreDenunciado) {
		this.nombreDenunciado = nombreDenunciado;
	}

	public List<RespuestaPanicoEntity> getRespuestaPanicos() {
		return this.respuestaPanicos;
	}

	public void setRespuestaPanicos(List<RespuestaPanicoEntity> respuestaPanicos) {
		this.respuestaPanicos = respuestaPanicos;
	}

	public RespuestaPanicoEntity addRespuestaPanico(RespuestaPanicoEntity respuestaPanico) {
		getRespuestaPanicos().add(respuestaPanico);
		respuestaPanico.setPanico(this);

		return respuestaPanico;
	}

	public RespuestaPanicoEntity removeRespuestaPanico(RespuestaPanicoEntity respuestaPanico) {
		getRespuestaPanicos().remove(respuestaPanico);
		respuestaPanico.setPanico(null);

		return respuestaPanico;
	}

	public Collection<TipoPanicoEntity> getTipoPanicos() {
		return this.tipoPanicos;
	}

	public void setTipoPanicos(Collection<TipoPanicoEntity> tipoPanicos) {
		this.tipoPanicos = tipoPanicos;
	}

}