package br.com.p2.model.avaliacao;

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
import br.com.p2.model.veiculos.ClienteVeiculo;
import br.com.p2.model.veiculos.Modelo;
import br.com.p2.model.veiculos.Vendedor;


@Entity
@Table(name="avaliacoes") //indica o nome da tablea
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_conta",referencedColumnName="id")
	private Contas conta	;
	
	@ManyToOne()
	@JoinColumn(name="id_modelo",referencedColumnName="id")
	private Modelo modelo;
	
	@ManyToOne()
	@JoinColumn(name="id_cliente",referencedColumnName="id")
	private ClienteVeiculo cliente;
	
	@ManyToOne()
	@JoinColumn(name="id_usuario",referencedColumnName="id")
	private Usuarios usuario;
	
	@ManyToOne()
	@JoinColumn(name="id_vendedor",referencedColumnName="id")
	private Vendedor vendedor;
	
	
	@ManyToOne()
	@JoinColumn(name="id_empresa",referencedColumnName="id")
	private Empresas empresa;
	
	
	@Column(name="placa", length=20, nullable=false)
	private String placa;
	
	@Column(name="chassi", length=30, nullable=false)
	private String chassi;
	
	@Column(name="km")
	private Long km;
	
	@Column(name="renavam", length=30, nullable=false)
	private String renavam;
	

	@Temporal(TemporalType.DATE)
	@Column(name="data")
	private Date dataAvaliacao;
	
	@Column(name="data_liberacao")
	private Date dataLiberacao;
	
	@Column(name="valor", length=30, nullable=false)
	private Float valor;
	
	@Column(name="obs", length=300, nullable=false)
	private String obs;
	
	@Column(name="liberado", length=2)
	private String liberada;
	
	@Column(name="status", length=2, nullable=false)
	private String status;
	
	public Avaliacao() {
		// TODO Auto-generated constructor stub
	}

	public Avaliacao(Long id, Contas conta, Modelo modelo, ClienteVeiculo cliente, Usuarios usuario, Vendedor vendedor,
			Empresas empresa, String placa, String chassi, Long km, String renavam, Date dataAvaliacao,
			Date dataLiberacao, Float valor, String obs, String liberada, String status) {
		super();
		this.id = id;
		this.conta = conta;
		this.modelo = modelo;
		this.cliente = cliente;
		this.usuario = usuario;
		this.vendedor = vendedor;
		this.empresa = empresa;
		this.placa = placa;
		this.chassi = chassi;
		this.km = km;
		this.renavam = renavam;
		this.dataAvaliacao = dataAvaliacao;
		this.dataLiberacao = dataLiberacao;
		this.valor = valor;
		this.obs = obs;
		this.liberada = liberada;
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
		Avaliacao other = (Avaliacao) obj;
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

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public ClienteVeiculo getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVeiculo cliente) {
		this.cliente = cliente;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Empresas getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresas empresa) {
		this.empresa = empresa;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public Long getKm() {
		return km;
	}

	public void setKm(Long km) {
		this.km = km;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public Date getDataAvaliacao() {
		
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public Date getDataLiberacao() {
		return dataLiberacao;
	}

	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getLiberada() {
		return liberada;
	}
	
	public void setLiberada(String liberada) {
		this.liberada = liberada;
	}
	
	
	
	
	

}
