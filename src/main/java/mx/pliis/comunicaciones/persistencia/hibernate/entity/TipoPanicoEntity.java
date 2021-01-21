package mx.pliis.comunicaciones.persistencia.hibernate.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tipo_panico database table.
 * 
 */
@Entity
@Table(name = "tipo_panico")
@NamedQuery(name = "TipoPanicoEntity.findAll", query = "SELECT t FROM TipoPanicoEntity t")
public class TipoPanicoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_panico", unique = true, nullable = false)
	private Integer idTipoPanico;

	@Column(nullable = false, length = 100)
	private String codigo;

	@Column(nullable = false, length = 2147483647)
	private String descripcion;

	@Column(name = "email_contacto", length = 2147483647)
	private String emailContacto;

	@Column(nullable = false, length = 50)
	private String nombre;

	@Column(nullable = false, length = 2147483647)
	private String url;

	// bi-directional many-to-many association to PanicoEntity
	@ManyToMany(mappedBy = "tipoPanicos", fetch = FetchType.LAZY)
	private Collection<PanicoEntity> panicos;

	public TipoPanicoEntity() {
	}

	public Integer getIdTipoPanico() {
		return this.idTipoPanico;
	}

	public void setIdTipoPanico(Integer idTipoPanico) {
		this.idTipoPanico = idTipoPanico;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEmailContacto() {
		return this.emailContacto;
	}

	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Collection<PanicoEntity> getPanicos() {
		return this.panicos;
	}

	public void setPanicos(Collection<PanicoEntity> panicos) {
		this.panicos = panicos;
	}

}