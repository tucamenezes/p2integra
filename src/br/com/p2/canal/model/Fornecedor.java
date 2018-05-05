package br.com.p2.canal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.p2.model.Contas;

@Entity
@Table(name="canal_fornecedores") //indica o nome da tablea
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id  //indica a chave primaria
	//@GeneratedValue(strategy=GenerationType.IDENTITY) //cria um codigo autoincrement
	@Column(name="id")  //define o nome da coluna 
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_conta",referencedColumnName="id")
	private Contas conta	;
	
	@Column(name="id_fornecedor_externo")   
	private Integer codFornecedorExterno;
	
	@Column(name="nome", length=100) 
	private String nome;
	
	@Column(name="data_cadastro") //define o nome da coluna, tamanho e se pode ser null
	@Temporal(TemporalType.DATE) // para considerar apenas a data.
	private Date dataCadastro;
	
	@Column(name="status", length=2) 
	private String status;

	public Fornecedor(Long id, Contas conta, Integer codFornecedorExterno, String nome, Date dataCadastro,
			String status) {
		this.id = id;
		this.conta = conta;
		this.codFornecedorExterno = codFornecedorExterno;
		this.nome = nome;
		this.dataCadastro = dataCadastro;
		this.status = status;
	}
	
	
	public Fornecedor() {
		// TODO Auto-generated constructor stub
	}

	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
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
		Fornecedor other = (Fornecedor) obj;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
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


	public Integer getCodFornecedorExterno() {
		return codFornecedorExterno;
	}


	public void setCodFornecedorExterno(Integer codFornecedorExterno) {
		this.codFornecedorExterno = codFornecedorExterno;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	

}
