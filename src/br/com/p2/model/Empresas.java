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



@Entity
@Table(name="empresas")
public class Empresas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_conta",referencedColumnName="id")
	private Contas conta	;
	
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	
	@Column(name="cnpj")
	private Long cnpj;
	
	@Column(name="nome_contato", length=20)
	private String nomeContato;
	
	@Column(name="endereco", length=20)
	private String endereco;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_estado",referencedColumnName="id")
	private Estados estados;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_cidade",referencedColumnName="id")
	private Cidades cidades = new Cidades();
	
	@Column(name="email", length=200)
	private String email;
	
	@Column(name="telefone", length=20)
	private String telefone;
	
	@Column(name="insc_estadual", length=20)
	private String inscricaoEstadual;
	
	@Column(name="insc_municipal", length=20)
	private String inscricaoMunicipal;
	
	@Column(name="status", length=2)	
	private String status;
	
	
	
	
	public Empresas() {
		// TODO Auto-generated constructor stub
	}


	


	public Empresas(Long id, Contas conta, String nome, Long cnpj, String nomeContato, String endereco, Estados estados,
			Cidades cidades, String email, String telefone, String inscricaoEstadual, String inscricaoMunicipal,
			String status) {
		super();
		this.id = id;
		this.conta = conta;
		this.nome = nome;
		this.cnpj = cnpj;
		this.nomeContato = nomeContato;
		this.endereco = endereco;
		this.estados = estados;
		this.cidades = cidades;
		this.email = email;
		this.telefone = telefone;
		this.inscricaoEstadual = inscricaoEstadual;
		this.inscricaoMunicipal = inscricaoMunicipal;
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
		Empresas other = (Empresas) obj;
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


	public Long getCnpj() {
		return cnpj;
	}


	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}


	public String getNomeContato() {
		return nomeContato;
	}


	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public Estados getEstados() {
		return estados;
	}


	public void setEstados(Estados estados) {
		this.estados = estados;
	}


	public Cidades getCidades() {
		return cidades;
	}


	public void setCidades(Cidades cidades) {
		this.cidades = cidades;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}


	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}


	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}


	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}





	public Contas getConta() {
		return conta;
	}





	public void setConta(Contas conta) {
		this.conta = conta;
	}
	
	
	
	
	
	
}
