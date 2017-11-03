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
@Table(name="modelos") //indica o nome da tablea
public class Modelo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_conta",referencedColumnName="id")
	private Contas conta	;
	
	@ManyToOne()
	@JoinColumn(name="id_produto",referencedColumnName="id")
	private Produto produto;
		
	@Column(name="descricao", length=100, nullable=false)
	private String descricao;
	
	@Column(name="combustivel", length=20)	
	private String combustivel;
	
	@Column(name="ano_modelo", length=4)	
	private String anoModelo;
	
	@Column(name="ano_fabricacao", length=4)	
	private String anoFabricacao;
	
	@ManyToOne()
	@JoinColumn(name="id_cor_externa",referencedColumnName="id")
	private CorExterna corExterna;
	
	@ManyToOne()
	@JoinColumn(name="id_cor_interna",referencedColumnName="id")
	private CorInterna corInterna;
	
	@Column(name="numero_portas")	
	private Integer numeroPortas;
	
	@Column(name="status", length=2)	
	private String status;
	
	public Modelo() {
		// TODO Auto-generated constructor stub
	}
   
	
	
	
	

	public Modelo(Long id, Contas conta, Produto produto, String descricao, String combustivel, String anoModelo,
			String anoFabricacao, CorExterna corExterna, CorInterna corInterna, Integer numeroPortas, String status) {
		super();
		this.id = id;
		this.conta = conta;
		this.produto = produto;
		this.descricao = descricao;
		this.combustivel = combustivel;
		this.anoModelo = anoModelo;
		this.anoFabricacao = anoFabricacao;
		this.corExterna = corExterna;
		this.corInterna = corInterna;
		this.numeroPortas = numeroPortas;
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
		Modelo other = (Modelo) obj;
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


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getCombustivel() {
		return combustivel;
	}

	
	
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}


	public String getAnoModelo() {
		return anoModelo;
	}


	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}


	public String getAnoFabricacao() {
		return anoFabricacao;
	}


	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}



	public CorExterna getCorExterna() {
		return corExterna;
	}


	public void setCorExterna(CorExterna corExterna) {
		this.corExterna = corExterna;
	}


	public CorInterna getCorInterna() {
		return corInterna;
	}


	public void setCorInterna(CorInterna corInterna) {
		this.corInterna = corInterna;
	}



	public Integer getNumeroPortas() {
		return numeroPortas;
	}



	public void setNumeroPortas(Integer numeroPortas) {
		this.numeroPortas = numeroPortas;
	}



	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	

}
