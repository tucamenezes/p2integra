package br.com.p2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.p2.util.GenerateHashPasswordUtil;

@Entity
@Table(name="usuarios") //indica o nome da tablea
public class Usuarios implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_conta",referencedColumnName="id")
	private Contas conta	;
	
	
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	
	@Column(name="username", length=20, nullable=false)
	private String username;
	
	@Column(name="password", length=20, nullable=false)
	private String password;
	
	@Column(name="perfil", length=10)	
	private String perfil;
	
	
	
	@Column(name="status", length=2)	
	private String status;
	
	@Column(name="imagem", columnDefinition="text")
	private String imagem;
	
	
	
	public Usuarios() {
		
	}

	public Usuarios(Long id, Contas conta, String nome, String username, String password, String perfil, String status, String imagem) {
		super();
		this.id = id;
		this.nome = nome;
		this.conta = conta;
		this.username = username;
		this.password = password;
		this.perfil = perfil;
		this.status = status;
		this.imagem = imagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Usuarios other = (Usuarios) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Contas getConta() {
		return conta;
	}
	
	public void setConta(Contas conta) {
		this.conta = conta;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password =  password;
	}

	public String getPerfil() {
		return perfil;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
		

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getImagem() {
		return imagem;
	}
	
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	
	

}
