package br.com.p2.model.orcamento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity //indica que a classe representara uma tabela na base de dados
@Table(name="gestores") //indica o nome da tablea
public class Gestor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id  //indica a chave primaria
	@Column(name="id_gestor", length=10, nullable=false) 
	private String idGestor;
	
	@Column(name="nome", length=50, nullable=false) //define o nome da coluna, tamanho e se pode ser null
	private String nome;
	
	@Column(name="status", length=2, nullable=false) //define o nome da coluna, tamanho e se pode ser null
	private String status;
	
	

	
	
	//construtores
	public Gestor() {
		
	}
	


    //hashcode e equas

	public Gestor(String idGestor, String nome, String status) {
		this.idGestor = idGestor;
		this.nome = nome;
		this.status = status;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGestor == null) ? 0 : idGestor.hashCode());
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
		Gestor other = (Gestor) obj;
		if (idGestor == null) {
			if (other.idGestor != null)
				return false;
		} else if (!idGestor.equals(other.idGestor))
			return false;
		return true;
	}


	public String getIdGestor() {
		return idGestor;
	}


	public void setIdGestor(String idGestor) {
		this.idGestor = idGestor;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
