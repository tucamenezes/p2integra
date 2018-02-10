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
	@JoinColumn(name="id_fornecedor", referencedColumnName="id")
	private Fornecedor fornecedor;
	
	@Column(name="data") //define o nome da coluna, tamanho e se pode ser null
	@Temporal(TemporalType.DATE) // para considerar apenas a data.
	private Date data;
	
	@Column(name="nfe_xml", columnDefinition="text")
	private String nfeXml;

	public NFE(Long id, Contas conta, Integer idNfeExterno, Fornecedor fornecedor, Date data, String nfeXml) {
		super();
		this.id = id;
		this.conta = conta;
		this.idNfeExterno = idNfeExterno;
		this.fornecedor = fornecedor;
		this.data = data;
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


	public Fornecedor getFornecedor() {
		return fornecedor;
	}


	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
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
	
	

	
	
	
}
