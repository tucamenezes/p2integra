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
import br.com.p2.model.Usuarios;



@Entity
@Table(name="proposta_veiculos_log") //indica o nome da tablea

public class LogPropostaVeiculos implements Serializable {


		private static final long serialVersionUID = 1L;
		
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;	
		
		@ManyToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="id_conta",referencedColumnName="id")
		private Contas conta	;
		
		@ManyToOne()
		@JoinColumn(name="id_proposta_p2",referencedColumnName="id")
		private PropostaVeiculo proposta;
		

		@Temporal(TemporalType.DATE)
		@Column(name="data")
		private Date dataLOG;
		
		@Column(name="acao", length=2)
		private String acao;
		
		@Column(name="motivo", length=50)
		private Date motivo;
		
		@Column(name="valor_liberado")
		private Double valorLiberado;

		@ManyToOne()
		@JoinColumn(name="id_responsavel",referencedColumnName="id")
		private Usuarios responsavel;
		
		

	public LogPropostaVeiculos() {
		// TODO Auto-generated constructor stub
	}



	public LogPropostaVeiculos(Long id, Contas conta, PropostaVeiculo proposta, Date dataLOG, String acao, Date motivo,
			Double valorLiberado, Usuarios responsavel) {
		super();
		this.id = id;
		this.conta = conta;
		this.proposta = proposta;
		this.dataLOG = dataLOG;
		this.acao = acao;
		this.motivo = motivo;
		this.valorLiberado = valorLiberado;
		this.responsavel = responsavel;
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
		LogPropostaVeiculos other = (LogPropostaVeiculos) obj;
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



	public PropostaVeiculo getProposta() {
		return proposta;
	}



	public void setProposta(PropostaVeiculo proposta) {
		this.proposta = proposta;
	}



	public Date getDataLOG() {
		return dataLOG;
	}



	public void setDataLOG(Date dataLOG) {
		this.dataLOG = dataLOG;
	}



	public String getAcao() {
		return acao;
	}



	public void setAcao(String acao) {
		this.acao = acao;
	}



	public Date getMotivo() {
		return motivo;
	}



	public void setMotivo(Date motivo) {
		this.motivo = motivo;
	}



	public Double getValorLiberado() {
		return valorLiberado;
	}



	public void setValorLiberado(Double valorLiberado) {
		this.valorLiberado = valorLiberado;
	}



	public Usuarios getResponsavel() {
		return responsavel;
	}



	public void setResponsavel(Usuarios responsavel) {
		this.responsavel = responsavel;
	}
	
	
	

	
	
}
