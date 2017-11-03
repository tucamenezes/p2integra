package br.com.p2.model.veiculos;

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

import br.com.p2.model.Contas;

@Entity
@Table(name="vendedores") //indica o nome da tablea
public class Vendedor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_conta",referencedColumnName="id")
	private Contas conta	;
	
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	
	
	@Column(name="nome_nbs", length=10)	
	private String nbsNome;
	
	@Column(name="status", length=2)	
	private String status;
	
	public Vendedor() {
		// TODO Auto-generated constructor stub
	}

	

	public Vendedor(Long id, Contas conta, String nome, String nbsNome, String status) {
		super();
		this.id = id;
		this.conta = conta;
		this.nome = nome;
		this.nbsNome = nbsNome;
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
		Vendedor other = (Vendedor) obj;
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
  

	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getNbsNome() {
		return nbsNome;
	}



	public void setNbsNome(String nbsNome) {
		this.nbsNome = nbsNome;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	
	
	

}
