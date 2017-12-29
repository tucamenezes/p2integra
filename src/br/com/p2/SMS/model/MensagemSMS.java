package br.com.p2.SMS.model;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.p2.model.Contas;
import br.com.p2.model.Empresas;


@Entity
public class MensagemSMS implements Serializable {
   
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_conta",referencedColumnName="id")
	private Contas conta	;
	
	@Column(name="codigo_cliente")  
	private Long codigoClienteExterno;
	
	@Column(name="cod_evento")  
	private Integer codigoEvento;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_empresa",referencedColumnName="id")
	private Empresas empresa;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_tipo",referencedColumnName="id")
	private TipoCampanhaSMS tipoSMS;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data")  
	private Date data;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_programada")  
	private Date dataProgramada;

	@Column(name="celular", length=30, nullable=false)  
	private String celular;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_recebimento")  
	private Date dataRecebimento;
	
	@Column(name="id_original_sms", length=50)  
	private String idOriginalSMS; 
	
	
	@Column(name="mensagem", length=200)  
	private String mensagem; 
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_integracao")  
	private Date dataIntegracao;
	
	@Column(name="status_integracao", length=1)  
	private String statusIntegracao; 
	
	public MensagemSMS() {
		// TODO Auto-generated constructor stub
	}

	

	public MensagemSMS(Long id, Contas conta, Long codigoClienteExterno, Integer codigoEvento, Empresas empresa,
			TipoCampanhaSMS tipoSMS, Date data, Date dataProgramada, String celular, Date dataRecebimento,
			String idOriginalSMS, String mensagem, Date dataIntegracao, String statusIntegracao) {
		super();
		this.id = id;
		this.conta = conta;
		this.codigoClienteExterno = codigoClienteExterno;
		this.codigoEvento = codigoEvento;
		this.empresa = empresa;
		this.tipoSMS = tipoSMS;
		this.data = data;
		this.dataProgramada = dataProgramada;
		this.celular = celular;
		this.dataRecebimento = dataRecebimento;
		this.idOriginalSMS = idOriginalSMS;
		this.mensagem = mensagem;
		this.dataIntegracao = dataIntegracao;
		this.statusIntegracao = statusIntegracao;
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
		MensagemSMS other = (MensagemSMS) obj;
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



	public Long getCodigoClienteExterno() {
		return codigoClienteExterno;
	}



	public void setCodigoClienteExterno(Long codigoClienteExterno) {
		this.codigoClienteExterno = codigoClienteExterno;
	}



	public Integer getCodigoEvento() {
		return codigoEvento;
	}



	public void setCodigoEvento(Integer codigoEvento) {
		this.codigoEvento = codigoEvento;
	}



	public Empresas getEmpresa() {
		return empresa;
	}



	public void setEmpresa(Empresas empresa) {
		this.empresa = empresa;
	}



	public TipoCampanhaSMS getTipoSMS() {
		return tipoSMS;
	}



	public void setTipoSMS(TipoCampanhaSMS tipoSMS) {
		this.tipoSMS = tipoSMS;
	}



	public Date getData() {
		return data;
	}



	public void setData(Date data) {
		this.data = data;
	}



	public Date getDataProgramada() {
		return dataProgramada;
	}



	public void setDataProgramada(Date dataProgramada) {
		this.dataProgramada = dataProgramada;
	}



	public String getCelular() {
		return celular;
	}



	public void setCelular(String celular) {
		this.celular = celular;
	}



	public Date getDataRecebimento() {
		return dataRecebimento;
	}



	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}



	public String getIdOriginalSMS() {
		return idOriginalSMS;
	}



	public void setIdOriginalSMS(String idOriginalSMS) {
		this.idOriginalSMS = idOriginalSMS;
	}



	public String getMensagem() {
		return mensagem;
	}



	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}



	public Date getDataIntegracao() {
		return dataIntegracao;
	}



	public void setDataIntegracao(Date dataIntegracao) {
		this.dataIntegracao = dataIntegracao;
	}



	public String getStatusIntegracao() {
		return statusIntegracao;
	}



	public void setStatusIntegracao(String statusIntegracao) {
		this.statusIntegracao = statusIntegracao;
	}


	
}
