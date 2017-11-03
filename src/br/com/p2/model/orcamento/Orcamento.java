package br.com.p2.model.orcamento;

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


@Entity //indica que a classe representara uma tabela na base de dados
@Table(name="orcamentos") //indica o nome da tablea
public class Orcamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
		@Id  //indica a chave primaria
		@GeneratedValue(strategy=GenerationType.IDENTITY) //cria um codigo autoincrement
		@Column(name="id")  //define o nome da coluna 
		private Long id;
		
		@ManyToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="id_conta",referencedColumnName="id")
		private Contas conta	;
		
		@Column(name="id_orcamento_nbs")  //define o nome da coluna 
		private Integer codOrcamentoNBS;
		
		@Column(name="empresa") //define o nome da coluna, tamanho e se pode ser null
		private Integer empresa;
		
		@Column(name="fornecedor", length=50, nullable=false) //define o nome da coluna, tamanho e se pode ser null
		private String fornecedor;
		
		@Column(name="valor") //define o nome da coluna, tamanho e se pode ser null
		private Double valor;
		
		@Column(name="competencia", length=20, nullable=false) //define o nome da coluna, tamanho e se pode ser null
		private String competencia;
		
		@Column(name="saldo") //define o nome da coluna, tamanho e se pode ser null
		private Double saldo;
		
		@ManyToOne()
		@JoinColumn(name="id_gestor", referencedColumnName="id_gestor")
		private Gestor gestor;
		
		@Column(name="liberado", length=2) 
		private String liberado;
		
		@Column(name="data_liberacao") //define o nome da coluna, tamanho e se pode ser null
		@Temporal(TemporalType.DATE) // para considerar apenas a data.
		private Date dataLiberacao;
		
		
		@ManyToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="usuario_liberacao",referencedColumnName="id")
		private Usuarios usuarioLiberou;
		
		
		@Column(name="status", length=2, nullable=false) //define o tipo do status do Orcamento
		private String status;
		
		@Column(name="data_competencia") //define o nome da coluna, tamanho e se pode ser null
		@Temporal(TemporalType.DATE) // para considerar apenas a data.
		private Date dataCompetencia;
		
		
		
		//construtores
		public Orcamento() {
			
		}





		public Orcamento(Long id, Contas conta, Integer codOrcamentoNBS, Integer empresa, String fornecedor,
				Double valor, String competencia, Double saldo, Gestor gestor, String liberado, Date dataLiberacao,
				Usuarios usuarioLiberou, String status, Date dataCompetencia) {
			super();
			this.id = id;
			this.conta = conta;
			this.codOrcamentoNBS = codOrcamentoNBS;
			this.empresa = empresa;
			this.fornecedor = fornecedor;
			this.valor = valor;
			this.competencia = competencia;
			this.saldo = saldo;
			this.gestor = gestor;
			this.liberado = liberado;
			this.dataLiberacao = dataLiberacao;
			this.usuarioLiberou = usuarioLiberou;
			this.status = status;
			this.dataCompetencia = dataCompetencia;
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
			Orcamento other = (Orcamento) obj;
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





		public Integer getCodOrcamentoNBS() {
			return codOrcamentoNBS;
		}





		public void setCodOrcamentoNBS(Integer codOrcamentoNBS) {
			this.codOrcamentoNBS = codOrcamentoNBS;
		}





		public Integer getEmpresa() {
			return empresa;
		}





		public void setEmpresa(Integer empresa) {
			this.empresa = empresa;
		}





		public String getFornecedor() {
			return fornecedor;
		}





		public void setFornecedor(String fornecedor) {
			this.fornecedor = fornecedor;
		}





		public Double getValor() {
			return valor;
		}





		public void setValor(Double valor) {
			this.valor = valor;
		}





		public String getCompetencia() {
			return competencia;
		}





		public void setCompetencia(String competencia) {
			this.competencia = competencia;
		}





		public Double getSaldo() {
			return saldo;
		}





		public void setSaldo(Double saldo) {
			this.saldo = saldo;
		}





		public Gestor getGestor() {
			return gestor;
		}





		public void setGestor(Gestor gestor) {
			this.gestor = gestor;
		}





		public String getLiberado() {
			return liberado;
		}





		public void setLiberado(String liberado) {
			this.liberado = liberado;
		}





		public Date getDataLiberacao() {
			return dataLiberacao;
		}





		public void setDataLiberacao(Date dataLiberacao) {
			this.dataLiberacao = dataLiberacao;
		}





		public Usuarios getUsuarioLiberou() {
			return usuarioLiberou;
		}





		public void setUsuarioLiberou(Usuarios usuarioLiberou) {
			this.usuarioLiberou = usuarioLiberou;
		}





		public String getStatus() {
			return status;
		}





		public void setStatus(String status) {
			this.status = status;
		}





		public Date getDataCompetencia() {
			return dataCompetencia;
		}





		public void setDataCompetencia(Date dataCompetencia) {
			this.dataCompetencia = dataCompetencia;
		}



		
		//methods gets and setters
		


}
