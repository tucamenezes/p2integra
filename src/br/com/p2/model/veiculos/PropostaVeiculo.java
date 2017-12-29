package br.com.p2.model.veiculos;

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
import br.com.p2.model.Empresas;
import br.com.p2.model.Usuarios;

@Entity
@Table(name="proposta_veiculos") //indica o nome da tablea
public class PropostaVeiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_conta",referencedColumnName="id")
	private Contas conta	;
	
	@ManyToOne()
	@JoinColumn(name="id_empresa",referencedColumnName="id")
	private Empresas empresa;
	
	
	@Column(name="veiculo", length=150)
	private String veiculo;
	
	
	@Column(name="cliente", length=150)
	private String cliente;
	
	
	@ManyToOne()
	@JoinColumn(name="id_vendedor",referencedColumnName="id")
	private Vendedor vendedor;
	
	@Column(name="cod_proposta")
	private Long idPropostaNBS;
	
	@Column(name="valor_tabela")
	private Double valorTabela;

	@Column(name="valor_desconto")
	private Double valorDesconto;
	
	@Column(name="valor_negociado")
	private Double valorNegociado;
	
	@Column(name="margem")
	private Double margem;
	

	@Temporal(TemporalType.DATE)
	@Column(name="data_proposta")
	private Date dataProposta;
	
	@Column(name="liberado", length=2)
	private String liberada;
	
	@Column(name="data_liberacao")
	private Date dataLiberacao;

	@ManyToOne()
	@JoinColumn(name="id_liberador",referencedColumnName="id")
	private Usuarios liberador;
	
	@Column(name="status", length=2, nullable=false)
	private String status;
	
	
	public PropostaVeiculo() {
		// TODO Auto-generated constructor stub
	}


	public PropostaVeiculo(Long id, Contas conta, Empresas empresa, String veiculo, String cliente,
			Vendedor vendedor, Long idPropostaNBS, Double valorTabela, Double valorDesconto, Double valorNegociado,
			Double margem, Date dataProposta, String liberada, Date dataLiberacao, Usuarios liberador, String status) {
		super();
		this.id = id;
		this.conta = conta;
		this.empresa = empresa;
		this.veiculo = veiculo;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.idPropostaNBS = idPropostaNBS;
		this.valorTabela = valorTabela;
		this.valorDesconto = valorDesconto;
		this.valorNegociado = valorNegociado;
		this.margem = margem;
		this.dataProposta = dataProposta;
		this.liberada = liberada;
		this.dataLiberacao = dataLiberacao;
		this.liberador = liberador;
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
		PropostaVeiculo other = (PropostaVeiculo) obj;
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


	public Empresas getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresas empresa) {
		this.empresa = empresa;
	}


	public String getVeiculo() {
		return veiculo;
	}


	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}


	public String getCliente() {
		return cliente;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public Vendedor getVendedor() {
		return vendedor;
	}


	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}


	public Long getIdPropostaNBS() {
		return idPropostaNBS;
	}


	public void setIdPropostaNBS(Long idPropostaNBS) {
		this.idPropostaNBS = idPropostaNBS;
	}


	public Double getValorTabela() {
		return valorTabela;
	}


	public void setValorTabela(Double valorTabela) {
		this.valorTabela = valorTabela;
	}


	public Double getValorDesconto() {
		return valorDesconto;
	}


	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}


	public Double getValorNegociado() {
		return valorNegociado;
	}


	public void setValorNegociado(Double valorNegociado) {
		this.valorNegociado = valorNegociado;
	}


	public Double getMargem() {
		return margem;
	}


	public void setMargem(Double margem) {
		this.margem = margem;
	}


	public Date getDataProposta() {
		return dataProposta;
	}


	public void setDataProposta(Date dataProposta) {
		this.dataProposta = dataProposta;
	}


	public String getLiberada() {
		return liberada;
	}


	public void setLiberada(String liberada) {
		this.liberada = liberada;
	}


	public Date getDataLiberacao() {
		return dataLiberacao;
	}


	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}


	public Usuarios getLiberador() {
		return liberador;
	}


	public void setLiberador(Usuarios liberador) {
		this.liberador = liberador;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
