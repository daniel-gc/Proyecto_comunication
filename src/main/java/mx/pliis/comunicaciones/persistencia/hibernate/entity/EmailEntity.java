package mx.pliis.comunicaciones.persistencia.hibernate.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the email database table.
 * 
 */
@Entity
@Table(name="email")
@NamedQuery(name="EmailEntity.findAll", query="SELECT e FROM EmailEntity e")
public class EmailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=100)
	private String email;

	@Column(nullable=false, length=100)
	private String pass;

	@Column(nullable=false, length=100)
	private String smtp;

	@Column(name="ssl_port", nullable=false)
	private Integer sslPort;

	@Column(name="tls_port", nullable=false)
	private Integer tlsPort;

	public EmailEntity() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSmtp() {
		return this.smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public Integer getSslPort() {
		return this.sslPort;
	}

	public void setSslPort(Integer sslPort) {
		this.sslPort = sslPort;
	}

	public Integer getTlsPort() {
		return this.tlsPort;
	}

	public void setTlsPort(Integer tlsPort) {
		this.tlsPort = tlsPort;
	}

}