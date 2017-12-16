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
@Table(name="contas") //indica o nome da tablea
public class Contas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")	
	private Long id;
	
	@Column(name="nome", length=100)
	private String  nome;
	
	@Column(name="hash_validation", length=100)
	private String hashValidation;
	
	@Column(name="status", length=2, nullable=false)
	private String status;
	
	
	public Contas() {
		// TODO Auto-generated constructor stub
	}


	public Contas(Long id, String nome, String hashValidation, String status) {
		super();
		this.id = id;
		this.nome = nome;
		this.hashValidation = hashValidation;
		this.status = status;
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
		Contas other = (Contas) obj;
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


	

	public String getHashValidation() {
		return hashValidation;
	}


	public void setHashValidation(String hashValidation) {
		this.hashValidation = hashValidation;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
