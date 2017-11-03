package br.com.p2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="acessos_usuarios") //indica o nome da tablea
public class AcessosUsuarios implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="acesso", length=10, nullable=false)
	private String acesso;
	
	@ManyToOne()
	@JoinColumn(name="id_usuario",referencedColumnName="id")
	private Usuarios usuario;
	
	

	public AcessosUsuarios() {
		
	}



	public AcessosUsuarios(Long id, Usuarios usuario) {
		super();
		this.id = id;
		this.usuario = usuario;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcessosUsuarios other = (AcessosUsuarios) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Usuarios getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	
	
	public String getAcesso() {
		return acesso;
	}
	
	public void setAcesso(String acesso) {
		this.acesso = acesso;
	}
	
	
	
}
