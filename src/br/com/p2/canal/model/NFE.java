package br.com.p2.canal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.IndexColumn;

import br.com.p2.model.Contas;
import br.com.p2.model.Empresas;

@Entity
@Table(name="canal_nfe_xml") //indica o nome da tablea
public class NFE implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id  //indica a chave primaria
	@GeneratedValue(strategy=GenerationType.IDENTITY) //cria um codigo autoincrement
	@Column(name="id")  //define o nome da coluna 
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_conta",referencedColumnName="id")
	private Contas conta	;
	
	@Column(name="id_nfe_externo")   
	private Integer idNfeExterno;
	
	@ManyToOne()
	@JoinColumn(name="id_empresa",referencedColumnName="id")
	private Empresas empresa;
	
	@ManyToMany
    @JoinTable(name="canal_fornecedores_nfe", joinColumns=
    {@JoinColumn(name="id_nfe")}, inverseJoinColumns=
      {@JoinColumn(name="id_fornecedor")})
	private List<Fornecedor> fornecedores;
	
	@Column(name="data") //define o nome da coluna, tamanho e se pode ser null
	@Temporal(TemporalType.DATE) // para considerar apenas a data.
	private Date data;
	
	@Column(name="nome_empresa", length=100) 
	private String nomeEmpresa;
	
	@Column(name="nfe_xml", columnDefinition="text")
	private String nfeXml;
	
	

	public NFE(Long id, Contas conta, Integer idNfeExterno, Empresas empresa, List<Fornecedor> fornecedores, Date data, String nomeEmpresa, String nfeXml) {
		super();
		this.id = id;
		this.conta = conta;
		this.idNfeExterno = idNfeExterno;
		this.empresa = empresa;
		this.fornecedores = fornecedores;
		this.data = data;
		this.nomeEmpresa = nomeEmpresa;
		this.nfeXml = nfeXml;
		
	}
	
	
	public NFE() {
		// TODO Auto-generated constructor stub
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
		NFE other = (NFE) obj;
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


	public Integer getIdNfeExterno() {
		return idNfeExterno;
	}


	public void setIdNfeExterno(Integer idNfeExterno) {
		this.idNfeExterno = idNfeExterno;
	}


	public List<Fornecedor> getFornecedor() {
		return fornecedores;
	}


	public void setFornecedor(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}


	public String getNfeXml() {
		return nfeXml;
	}


	public void setNfeXml(String nfeXml) {
		this.nfeXml = nfeXml;
	}
	
	
	public Empresas getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresas empresa) {
		this.empresa = empresa;
	}
	
	
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	
	
	
}
