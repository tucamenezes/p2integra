package br.com.p2.canal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	private Integer numeroNota;
	
	@Column(name="serie_nota")   
	private String serieNota; 
	
	@Column(name="id_transacao")   
	private Integer transacaoVenda;
	
	@Column(name="id_empresa")   
	private Integer idEmpresa;
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="canal_fornecedores_nfe", joinColumns= {@JoinColumn(name="id_nfe")}, 
               inverseJoinColumns= {@JoinColumn(name="id_fornecedor")})
	private List<Fornecedor> fornecedores;
	
	@Column(name="data") //define o nome da coluna, tamanho e se pode ser null
	@Temporal(TemporalType.DATE) // para considerar apenas a data.
	private Date dataSaida;
	
	@Column(name="nome_empresa", length=100) 
	private String nomeEmpresa;
	
	@Column(name="chave_nfe", length=255) 
	private String chaveNFE;
	
	@Column(name="nfe_xml", columnDefinition="text")
	private String xmlNFE;
	
	

	public NFE(Long id, Contas conta, Integer numeroNota, String serieNota ,Integer transacaoVenda, Integer idEmpresa, List<Fornecedor> fornecedores, Date dataSaida, String nomeEmpresa, String chaveNFE, String xmlNFE) {
		super();
		this.id = id;
		this.conta = conta;
		this.numeroNota = numeroNota;
		this.serieNota = serieNota;
		this.transacaoVenda = transacaoVenda;
		this.idEmpresa = idEmpresa;
		this.fornecedores = fornecedores;
		this.dataSaida = dataSaida;
		this.nomeEmpresa = nomeEmpresa;
		this.chaveNFE = chaveNFE;
		this.xmlNFE = xmlNFE;
		
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



	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}


	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}


	public Date getDataSaida() {
		return dataSaida;
	}
	
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	

	public Integer getIdEmpresa() {
		return idEmpresa;
	}


	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}


	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}


	public Integer getNumeroNota() {
		return numeroNota;
	}


	public void setNumeroNota(Integer numeroNota) {
		this.numeroNota = numeroNota;
	}


	public String getSerieNota() {
		return serieNota;
	}


	public void setSerieNota(String serieNota) {
		this.serieNota = serieNota;
	}


	public Integer getTransacaoVenda() {
		return transacaoVenda;
	}


	public void setTransacaoVenda(Integer transacaoVenda) {
		this.transacaoVenda = transacaoVenda;
	}


	public String getChaveNFE() {
		return chaveNFE;
	}  


	public void setChaveNFE(String chaveNFE) {
		this.chaveNFE = chaveNFE;
	}


	public String getXmlNFE() {
		return xmlNFE;
	}


	public void setXmlNFE(String xmlNFE) {
		this.xmlNFE = xmlNFE;
	}

	
	
	
}
