package mx.pliis.comunicaciones.persistencia.hibernate.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the propiedad database table.
 * 
 */
@Entity
@Table(name="propiedad")
@NamedQuery(name="PropiedadEntity.findAll", query="SELECT p FROM PropiedadEntity p")
public class PropiedadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_propiedad", unique=true, nullable=false)
	private Integer idPropiedad;

	@Column(name="id_afil_red_social", nullable=false)
	private Integer idAfilRedSocial;

	@Column(nullable=false, length=50)
	private String nombre;

	//bi-directional many-to-one association to PropiedadConversacionEntity
	@OneToMany(mappedBy="propiedad")
	private List<PropiedadConversacionEntity> propiedadConversacions;

	public PropiedadEntity() {
	}

	public Integer getIdPropiedad() {
		return this.idPropiedad;
	}

	public void setIdPropiedad(Integer idPropiedad) {
		this.idPropiedad = idPropiedad;
	}

	public Integer getIdAfilRedSocial() {
		return this.idAfilRedSocial;
	}

	public void setIdAfilRedSocial(Integer idAfilRedSocial) {
		this.idAfilRedSocial = idAfilRedSocial;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PropiedadConversacionEntity> getPropiedadConversacions() {
		return this.propiedadConversacions;
	}

	public void setPropiedadConversacions(List<PropiedadConversacionEntity> propiedadConversacions) {
		this.propiedadConversacions = propiedadConversacions;
	}

	public PropiedadConversacionEntity addPropiedadConversacion(PropiedadConversacionEntity propiedadConversacion) {
		getPropiedadConversacions().add(propiedadConversacion);
		propiedadConversacion.setPropiedad(this);

		return propiedadConversacion;
	}

	public PropiedadConversacionEntity removePropiedadConversacion(PropiedadConversacionEntity propiedadConversacion) {
		getPropiedadConversacions().remove(propiedadConversacion);
		propiedadConversacion.setPropiedad(null);

		return propiedadConversacion;
	}

}