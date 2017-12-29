package br.com.p2.SMS.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.p2.model.Contas;

@Entity
public class TipoCampanhaSMS implements Serializable {

	
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_conta",referencedColumnName="id")
	private Contas conta	;
	
	@Column(name="descricao", length=20, nullable=false)  
	private String descricao;
	
	@Column(name="status", length=1)  
	private String status;
	
	public TipoCampanhaSMS() {
		// TODO Auto-generated constructor stub
	}

	public TipoCampanhaSMS(Long id, Contas conta, String descricao, String status) {
		super();
		this.id = id;
		this.conta = conta;
		this.descricao = descricao;
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
		TipoCampanhaSMS other = (TipoCampanhaSMS) obj;
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

	public Contas getConta() {
		return conta;
	}

	public void setConta(Contas conta) {
		this.conta = conta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	
	
}
